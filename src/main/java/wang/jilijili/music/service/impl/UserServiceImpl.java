package wang.jilijili.music.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wang.jilijili.music.common.utils.IpUtils;
import wang.jilijili.music.config.SecurityConfig;
import wang.jilijili.music.exception.BizException;
import wang.jilijili.music.exception.ExceptionType;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.convert.UserConvert;
import wang.jilijili.music.pojo.dto.CreateTokenDto;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.dto.UserQueryDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.pojo.vo.UserVo;
import wang.jilijili.music.service.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-02-12 15:32:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    UserMapper userMapper;

    SessionRegistry sessionRegistry;
    private final UserConvert userConvert;

    PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserMapper userMapper, SessionRegistry sessionRegistry, UserConvert userConvert, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.sessionRegistry = sessionRegistry;
        this.userConvert = userConvert;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto get(String id) {
        User user = this.userMapper.selectById(id);
        return userConvert.toDto(user);
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        User user = this.userMapper.selectById(id);
        User updateUser = null;
        if (user != null) {
            updateUser = userConvert.toUserEntity(userUpdateRequest);
            this.userMapper.update(updateUser,
                    new LambdaQueryWrapper<User>().eq(User::getId, user.getId()));
        }

        return userConvert.toDto(updateUser);
    }

    @Override
    public Result<?> delete(String id) {
        int delete = this.userMapper.deleteById(id);

        return delete >= 1 ? Result.ok() : Result.fail();
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto, HttpServletRequest request) {
        User user = userConvert.toUserEntity(userCreateDto);
        user.setId(KsuidGenerator.generate());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLastLoginIp(IpUtils.getIpAddress(request));
        user.setNickname(IpUtils.getUserAgent(request) + UUID.fastUUID().toString());
        this.userMapper.insert(user);
        return userConvert.toDto(user);

    }

    @Override
    public IPage<UserVo> getOnlineUsers(Long page, Long size, UserQueryDto userQueryDto) {
        IPage<User> ipage = new Page<>(page, size);
        // 得到在线用户
        List<Object> allPrincipals = this.sessionRegistry.getAllPrincipals();
        // 筛选条件
        LambdaQueryWrapper<User> queryWrapper = queryConditionConstruction(userQueryDto);
        // 执行查询
        queryWrapper
                .in(allPrincipals.size() > 0, User::getUsername, allPrincipals);
        IPage<User> iPage = this.page(ipage, queryWrapper);
        // 列表转换
        return iPage.convert(item -> {
            UserDto userDto = userConvert.toDto(item);
            return userConvert.toVo(userDto);

        });
    }

    /**
     * 查询条件构造
     *
     * @param userQueryDto
     * @return
     */
    private LambdaQueryWrapper<User> queryConditionConstruction(UserQueryDto userQueryDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(StringUtils.hasText(userQueryDto.getId()), User::getId, userQueryDto.getId())
                .eq(StringUtils.hasText(userQueryDto.getGender()), User::getGender, userQueryDto.getGender())
                .eq(StringUtils.hasText(userQueryDto.getUsername()), User::getUsername, userQueryDto.getUsername())
                .like(StringUtils.hasText(userQueryDto.getNickname()), User::getNickname, userQueryDto.getNickname())
                .eq(userQueryDto.getUnseal() != null, User::getUnseal, userQueryDto.getUnseal())
                .between(userQueryDto.getSpecifyTime() != null && userQueryDto.getCreatedTime() != null,
                        User::getCreatedTime, userQueryDto.getCreatedTime(), userQueryDto.getSpecifyTime())
                .orderBy(true, false, User::getCreatedTime);
        return queryWrapper;
    }

    @Override
    public IPage<UserVo> search(IPage<User> pageEntity, UserQueryDto userQueryDto) {
        IPage<User> page = this.page(pageEntity, queryConditionConstruction(userQueryDto));
        return page.convert(item -> userConvert.toVo(userConvert.toDto(item)));
    }

    @Override
    public String createToken(CreateTokenDto createTokenDto) {
        User user = this.loadUserByUsername(createTokenDto.getUsername());
        if (!this.passwordEncoder.matches(createTokenDto.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USERNAME_OR_PASSWORD_ERROR);
        }
        if (user.getUnseal() == null || user.getUnseal() < 1) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }
        if (user.getLocked() == null || user.getLocked() < 0) {
            throw new BizException(ExceptionType.USER_NOT_LOCKED);
        }

        return JWT.create()
                .withSubject(user.getUsername()) // 一般id,唯一字段
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME)) // 过期时间
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
    }


    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userMapper.getUserByUsername(username);
        if (user != null) {
            return user;
        }
        throw new BizException(ExceptionType.USERNAME_OR_PASSWORD_ERROR);
    }


    @Override
    public UserDto currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.loadUserByUsername(authentication.getName());
        return userConvert.toDto(user);
    }

    @Override
    public void exprot(UserQueryDto userQueryDto, HttpServletResponse response) {
        // 写入文件
        ExcelWriter writer = ExcelUtil.getWriter();
        try {
            // 设置响应类型
            response.setContentType("application/vnd.ms-excel");
            // 设置字符编码
            response.setCharacterEncoding("utf-8");
            // 设置响应头信息
            response.setHeader("Content-disposition",
                    "attachment;filename*=utf-8''" + URLEncoder.encode("用户数据", "UTF-8") + ".xlsx");


            LambdaQueryWrapper<User> queryWrapper = this.queryConditionConstruction(userQueryDto);
            List<User> userList = this.userMapper.selectList(queryWrapper);

            // 设置列头,可排序
            writer.addHeaderAlias("id", "id");
            writer.addHeaderAlias("username", "用户名称");
            writer.addHeaderAlias("nickname", "昵称");
            writer.addHeaderAlias("enabled", "是否可用");
            writer.addHeaderAlias("lastLoginIp", "IP地址");
            writer.addHeaderAlias("lastLoginTime", "最后登录时间");
            writer.addHeaderAlias("locked", "是否锁定");
            writer.addHeaderAlias("roles", "角色");
            writer.addHeaderAlias("password", "密码");
            writer.addHeaderAlias("gender", "性别");
            writer.addHeaderAlias("unseal", "是否锁定");
            writer.addHeaderAlias("createdTime", "创建时间");
            writer.addHeaderAlias("updatedTime", "更新时间");

            writer.merge(userList.size() - 1, "用户数据");

            // 设置单元格背景色
            StyleSet styleSet = writer.getStyleSet();
            styleSet.setBorder(BorderStyle.DASHED, IndexedColors.GOLD);


            //设置内容字体
            Font font = writer.createFont();
            font.setBold(true);
            font.setColor(Font.COLOR_RED);
            font.setItalic(true);
            writer.getStyleSet().setFont(font, true); //第二个参数表示是否忽略头部样式

            CellStyle headCellStyle = writer.getHeadCellStyle();
            headCellStyle.setWrapText(true);


            // 写入
            writer.write(userList, true);
            OutputStream outputStream = response.getOutputStream(); //得到输出流
            writer.flush(outputStream, true);

        } catch (IOException e) {
            throw new BizException(ExceptionType.USER_EXPORT_ERROR);
        } finally {
            writer.close();
        }


    }
}




