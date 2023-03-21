package wang.jilijili.common.core.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import wang.jilijili.common.constant.DatabaseConstant;
import wang.jilijili.common.constant.SecurityConstant;
import wang.jilijili.common.core.mapper.UserMapper;
import wang.jilijili.common.core.pojo.dto.CreateTokenDto;
import wang.jilijili.common.core.pojo.dto.CreateWeChatTokenDto;
import wang.jilijili.common.core.pojo.dto.QueryDto;
import wang.jilijili.common.core.pojo.dto.UserQueryDto;
import wang.jilijili.common.core.pojo.entity.User;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @author Amani
 * @date 2023年03月01日 10:41
 */

@Slf4j
@Getter
public class BaseController<M extends BaseMapper> implements UserDetailsService {

    protected M mapper;

    @Autowired
    public void setMapper(M mapper) {
        log.warn("初始化mapper对象:{}", mapper.getClass());
        this.mapper = mapper;
    }

    /**
     * 通过不通业务创建不通类型的token
     *
     * @param createTokenDto  token多种类型创建条件
     * @param passwordEncoder 加密方式
     * @return 返回token字符串
     */
    public String createToken(CreateTokenDto createTokenDto, PasswordEncoder passwordEncoder) {
        User user;
        if (createTokenDto instanceof CreateWeChatTokenDto weChatTokenDto) {
            user = this.loadUserByUsername(weChatTokenDto.getOpenId());
        } else {
            user = this.loadUserByUsername(createTokenDto.getUsername());
        }

        if (!passwordEncoder.matches(createTokenDto.getPassword(), user.getPassword())) {
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
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME)) // 过期时间
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()));
    }

    /**
     * 用户认证
     *
     * @param username the username identifying the user whose data is required.
     * @return 用户实体类
     * @throws UsernameNotFoundException 没有找到用户异常
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        if (mapper instanceof UserMapper userMapper) {
            User user = userMapper.getUserByUsername(username);
            if (user != null) {
                return user;
            }
            throw new BizException(ExceptionType.USERNAME_OR_PASSWORD_ERROR);
        }
        return null;
    }


    public void export(QueryDto queryDto, HttpServletResponse response) {
        // 写入文件
        try (ExcelWriter writer = ExcelUtil.getWriter()) {
            // 设置响应类型
            response.setContentType("application/vnd.ms-excel");
            // 设置字符编码
            response.setCharacterEncoding("utf-8");
            // 设置响应头信息
            response.setHeader("Content-disposition",
                    "attachment;filename*=utf-8''" + URLEncoder.encode("用户数据", StandardCharsets.UTF_8) + ".xlsx");

            QueryWrapper<T> queryWrapper = this.queryConditionConstruction(queryDto);
            List userList = mapper.selectList(queryWrapper);


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

            writer.merge(userList.size(), "用户数据");

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
        }


    }


    /**
     * 查询条件构造
     *
     * @param queryDto 查询基类
     * @return 返回不通查询构造器
     */
    private QueryWrapper<T> queryConditionConstruction(QueryDto queryDto) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (queryDto instanceof UserQueryDto userQueryDto) {

            // TODO [1]通过多个id导出用户
            queryWrapper
                    .eq(StringUtils.hasText(userQueryDto.getId()), DatabaseConstant.ID, userQueryDto.getId())
                    .eq(StringUtils.hasText(userQueryDto.getGender()), DatabaseConstant.GENDER, userQueryDto.getGender())
                    .eq(StringUtils.hasText(userQueryDto.getUsername()), DatabaseConstant.USERNAME, userQueryDto.getUsername())
                    .like(StringUtils.hasText(userQueryDto.getNickname()), DatabaseConstant.NICKNAME, userQueryDto.getNickname())
                    .eq(userQueryDto.getUnseal() != null, DatabaseConstant.ENABLED, userQueryDto.getUnseal())
                    .between(userQueryDto.getSpecifyTime() != null && userQueryDto.getCreatedTime() != null,
                            DatabaseConstant.CREATED_TIME, userQueryDto.getCreatedTime(), userQueryDto.getSpecifyTime())
                    .orderBy(true, false, DatabaseConstant.CREATED_TIME);
            return queryWrapper;
        }
        return queryWrapper;
    }


}