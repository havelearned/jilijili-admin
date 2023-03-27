package wang.jilijili.framework.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import wang.jilijili.common.constant.SecurityConstant;
import wang.jilijili.common.core.mapper.UserMapper;
import wang.jilijili.common.core.pojo.entity.User;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;

import java.io.IOException;
import java.util.Date;

/**
 * @author admin
 * @Date: 2023/1/28 16:38
 * @Description: 授权
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    UserMapper userMapper;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserMapper userMapper) {
        super(authenticationManager);
        this.userMapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        /*鉴权,校验解析token*/
        String header = request.getHeader(SecurityConstant.HEADER_STRING);
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {

        try {
            // 解析token
            DecodedJWT verify = JWT.require(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()))
                    .build()
                    .verify(header.replace(SecurityConstant.TOKEN_PREFIX, ""));

            Date expiresAt = verify.getExpiresAt();
            Date newDate = new Date();
            if (newDate.compareTo(expiresAt) < 0) {
                String username = verify.getSubject();
                if (username != null) {
                    User user = this.userMapper.getUserByUsername(username);
                    return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
                }
            } else {
                // 过期
                throw new BizException(ExceptionType.TOKEN_EXPIRES);
            }

        } catch (Exception e) {
            if (e instanceof TokenExpiredException exception) {
                throw new BizException(ExceptionType.TOKEN_EXPIRES);
            }
            log.error("token异常:{}", e.getMessage());

        }
        return new UsernamePasswordAuthenticationToken(null, null, null);


    }
}
