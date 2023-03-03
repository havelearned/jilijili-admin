package wang.jilijili.music.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import wang.jilijili.music.config.SecurityConfig;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.entity.User;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Auther: Amani
 * @Date: 2023/1/28 16:38
 * @Description: 授权
 */

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
        String header = request.getHeader(SecurityConfig.HEADER_STRING);
        if (header == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        // 解析token
        String username = JWT.require(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()))
                .build()
                .verify(header.replace(SecurityConfig.TOKEN_PREFIX, ""))
                .getSubject();

        if (username != null) {
            User user = this.userMapper.getUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        }

        return null;
    }
}
