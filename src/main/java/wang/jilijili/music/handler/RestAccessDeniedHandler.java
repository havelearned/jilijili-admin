package wang.jilijili.music.handler;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import wang.jilijili.music.exception.ErrorResponse;
import wang.jilijili.music.exception.ExceptionType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问被拒绝异常
 */

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.INNER_ERROR.getCode());
        errorResponse.setMessage("访问被拒绝异常");
        errorResponse.setTrace(JSON.toJSONString(accessDeniedException.getMessage()));

        response.getWriter().println(JSONUtil.parse(errorResponse));
        response.getWriter().flush();
    }
}
