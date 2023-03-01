package wang.jilijili.music.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import wang.jilijili.music.config.SecurityConfig;
import wang.jilijili.music.exception.BizException;
import wang.jilijili.music.exception.ExceptionType;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.bo.CreateTokenBo;
import wang.jilijili.music.pojo.dto.CreateWeChatTokenDto;
import wang.jilijili.music.pojo.entity.User;

import java.util.Date;

/**
 * @author Amani
 * @date 2023年03月01日 10:41
 */

@Slf4j
public class BaseController<T, M extends BaseMapper<T>, S extends IService> implements UserDetailsService {
    protected M mapper;
    protected S service;

    public BaseController(M mapper, S service) {
        this.mapper = mapper;
        this.service = service;
    }

    public String createToken(CreateTokenBo createTokenBo, PasswordEncoder passwordEncoder) {
        User user = null;
        //
        if (createTokenBo instanceof CreateWeChatTokenDto weChatTokenDto) {
            user = this.loadUserByUsername(weChatTokenDto.getOpenId());
        } else {
            user = this.loadUserByUsername(createTokenBo.getUsername());
        }


        if (!passwordEncoder.matches(createTokenBo.getPassword(), user.getPassword())) {
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
        if (mapper instanceof UserMapper userMapper) {
            User user = userMapper.getUserByUsername(username);
            if (user != null) {
                return user;
            }
            throw new BizException(ExceptionType.USERNAME_OR_PASSWORD_ERROR);
        }
        return null;
    }
}
