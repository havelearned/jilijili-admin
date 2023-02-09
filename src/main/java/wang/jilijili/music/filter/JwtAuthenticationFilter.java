package wang.jilijili.music.filter;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wang.jilijili.music.config.SecurityConfig;
import wang.jilijili.music.exception.BizException;
import wang.jilijili.music.exception.ExceptionType;
import wang.jilijili.music.handler.RestAuthenticationHandler;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.vo.Result;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Auther: Amani
 * @Date: 2023/1/28 16:15
 * @Description: 鉴权
 */

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   RestAuthenticationHandler authenticationHandler) {
        super(authenticationManager);
        setAuthenticationFailureHandler(authenticationHandler);
        setAuthenticationSuccessHandler(authenticationHandler);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        System.out.println("==========>" + authResult.getPrincipal().toString());
        String token = JWT.create()
                .withSubject(authResult.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));

        response.addHeader(SecurityConfig.HEADER_STRING, SecurityConfig.TOKEN_PREFIX + token);
        SecurityContextHolder.getContext().setAuthentication(authResult);

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>()
                    );
            getAuthenticationManager().authenticate(authenticationToken);
            return authenticationToken;

        } catch (Exception e) {
            try {

                response.getWriter().println(
                        JSON.toJSONString(Result.fail(ExceptionType.USER_NOT_FOND)));
                response.getWriter().flush();
                this.unsuccessfulAuthentication(request, response, null);
                throw new BizException(ExceptionType.USER_NOT_FOND);
            } catch (IOException | ServletException ex) {
                throw new RuntimeException(ex);
            }

        }


    }

}
