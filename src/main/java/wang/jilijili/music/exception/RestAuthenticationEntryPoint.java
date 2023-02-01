package wang.jilijili.music.exception;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证入口点
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {




    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.UNAUTHORIZED.getCode());
        errorResponse.setMessage(ExceptionType.UNAUTHORIZED.getMessage());
        errorResponse.setTrace(JSON.toJSONString(authException.getMessage()));

        response.getWriter().println(JSONUtil.parse(errorResponse));
        response.getWriter().flush();
    }
}
