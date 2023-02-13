package wang.jilijili.music.service.impl;

import cn.hutool.core.lang.UUID;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import jakarta.servlet.http.HttpServletRequest;
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
        user.setLastLoginIp(IpUtils.getIpSource(IpUtils.getIpAddress(request)));
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
                .like(StringUtils.hasText(userQueryDto.getId()), User::getId, userQueryDto.getId())
                .like(StringUtils.hasText(userQueryDto.getGender()), User::getGender, userQueryDto.getGender())
                .like(StringUtils.hasText(userQueryDto.getUsername()), User::getUsername, userQueryDto.getUsername())
                .like(StringUtils.hasText(userQueryDto.getNickname()), User::getNickname, userQueryDto.getNickname())
                .eq(true, User::getUnseal, userQueryDto.getUnseal())
                .between(userQueryDto.getSpecifyTime() != null && userQueryDto.getCreatedTime() != null,
                        User::getCreatedTime, userQueryDto.getCreatedTime(), userQueryDto.getSpecifyTime());
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
}




