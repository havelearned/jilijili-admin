package wang.jilijili.music.filter;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import wang.jilijili.music.config.SecurityConfig;
import wang.jilijili.music.exception.BizException;
import wang.jilijili.music.exception.ExceptionType;
import wang.jilijili.music.handler.RestAuthenticationHandler;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.vo.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Auther: Amani
 * @Date: 2023/1/28 16:15
 * @Description: 鉴权, 使用了tokenController创建内容,此类作废
 */
@Deprecated
@Component
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    public LoginAuthenticationFilter(AuthenticationManager authenticationManager,
                                     RestAuthenticationHandler authenticationHandler) {
        super(authenticationManager);
        setAuthenticationFailureHandler(authenticationHandler);
        setAuthenticationSuccessHandler(authenticationHandler);

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
            return this.getAuthenticationManager().authenticate(authenticationToken);

        } catch (Exception e) {
            try {
                response.getWriter()
                        .println(
                                JSON.toJSONString(
                                        Result.fail(ExceptionType.USERNAME_OR_PASSWORD_ERROR.getMessage())));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return null;
        }

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
//        System.out.println("==========>" + authResult.getPrincipal().toString());
        String token = JWT.create().withSubject(
                        authResult.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));


        SecurityContextHolder.getContext().setAuthentication(authResult);

        response.setContentType("application/json;charset=UTF-8");
        response.addHeader(SecurityConfig.HEADER_STRING, SecurityConfig.TOKEN_PREFIX + token);
        response.getWriter().println(JSON.toJSONString(Result.ok("登录成功")));
        response.getWriter().flush();


    }
}
