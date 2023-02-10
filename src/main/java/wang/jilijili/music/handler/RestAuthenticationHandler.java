package wang.jilijili.music.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import wang.jilijili.music.pojo.vo.Result;

import java.io.IOException;

@Component
public class RestAuthenticationHandler implements
        AuthenticationSuccessHandler,
        AuthenticationFailureHandler,
        LogoutSuccessHandler,
        SessionInformationExpiredStrategy,
        AccessDeniedHandler,
        AuthenticationEntryPoint {

    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    //认证失败处理
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        String detailMessage = authException.getClass().getSimpleName() + " " + authException.getLocalizedMessage();
        if (authException instanceof InsufficientAuthenticationException) {
            detailMessage = "请登陆后再访问";
        }
        response.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.getWriter().println(OBJECT_MAPPER.writeValueAsString(Result.fail(detailMessage)));
    }

    // 权限不足时的处理
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<String> result = new Result<>();
        result.setFlag(false);
        result.setData("");
        result.setCode(HttpStatus.UNAUTHORIZED.value());
        if (accessDeniedException instanceof MissingCsrfTokenException) {
            result.setMessage("缺少CSRF TOKEN,请从表单或HEADER传入");
        } else if (accessDeniedException instanceof InvalidCsrfTokenException) {
            result.setMessage("无效的CSRF TOKEN");
        } else if (accessDeniedException instanceof CsrfException) {
            result.setMessage(accessDeniedException.getLocalizedMessage());
        } else if (accessDeniedException instanceof AuthorizationServiceException) {
            result.setMessage(AuthorizationServiceException.class.getSimpleName() + " " + accessDeniedException.getLocalizedMessage());
        }
        response.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(OBJECT_MAPPER.writeValueAsString(result));
    }

    //认证失败时的处理
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(OBJECT_MAPPER.writeValueAsString(Result.fail("登陆失败! 用户名或者密码错误")));

    }

    //认证成功时的处理
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpStatus.OK.value());
        // SecurityContext在设置Authentication的时候并不会自动写入Session，读的时候却会根据Session判断，所以需要手动写入一次，否则下一次刷新时SecurityContext是新创建的实例。
        //  https://yangruoyu.blog.csdn.net/article/details/128276473
        request.getSession().setAttribute("spring_security_context_key", SecurityContextHolder.getContext());

        response.getWriter().println(OBJECT_MAPPER.writeValueAsString(Result.ok("登录成功")));

    }

    // 会话过期处理
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().println(OBJECT_MAPPER.writeValueAsString("注销成功"));
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        String message = "该账号已从其他设备登陆,如果不是您自己的操作请及时修改密码";
        System.out.println(message);
        final HttpServletResponse response = event.getResponse();
        response.setContentType(APPLICATION_JSON_CHARSET_UTF_8);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(OBJECT_MAPPER.writeValueAsString(event.getSessionInformation()));
        ;
    }
}
