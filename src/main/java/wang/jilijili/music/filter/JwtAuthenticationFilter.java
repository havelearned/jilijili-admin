package wang.jilijili.music.filter;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wang.jilijili.music.config.SecurityConfig;
import wang.jilijili.music.exception.ErrorResponse;
import wang.jilijili.music.exception.ExceptionType;
import wang.jilijili.music.pojo.entity.User;

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
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));

        response.addHeader(SecurityConfig.HEADER_STRING, SecurityConfig.TOKEN_PREFIX + token);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());

            return this.authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        // 鉴权失败处理
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.UNAUTHORIZED.getCode());
        errorResponse.setMessage(ExceptionType.UNAUTHORIZED.getMessage());
        errorResponse.setTrace(JSON.toJSONString(failed.getMessage()));
        response.getWriter().println(JSONUtil.parse(errorResponse));
        response.getWriter().flush();
    }

}
