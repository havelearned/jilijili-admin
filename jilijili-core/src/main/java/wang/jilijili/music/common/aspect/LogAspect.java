package wang.jilijili.music.common.aspect;

import com.github.ksuid.KsuidGenerator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.jilijili.music.common.enums.JilJilOperationLog;
import wang.jilijili.music.common.utils.IpUtils;
import wang.jilijili.music.exception.BizException;
import wang.jilijili.music.mapper.OperationLogMapper;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.entity.OperationLog;
import wang.jilijili.music.pojo.entity.User;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Amani
 * @date 2023年03月06日 10:28
 */
@Aspect
@Component
@Log
public class LogAspect {

    @Autowired
    OperationLogMapper operationLogMapper;

    @Autowired
    UserMapper userMapper;

    @Pointcut(value = "@annotation(wang.jilijili.music.common.enums.JilJilOperationLog)")
    public void operationLogPointcut() {

    }


    @Around(value = "operationLogPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        OperationLog operationLog = new OperationLog();
        JilJilOperationLog annotation = method.getAnnotation(JilJilOperationLog.class);
        if (annotation != null) {
            operationLog.setModuleName(annotation.moduleName());
            operationLog.setOperationType(annotation.type().ordinal());
        }
        operationLog.setMethodExecution(pjp.getTarget().getClass().getName() + "." + method.getName());
        operationLog.setRequestData(Arrays.toString(pjp.getArgs()));

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        operationLog.setRequestUrl(request.getRequestURI());
        operationLog.setLastLoginIp(IpUtils.getIpAddress(request));

        Object proceed = pjp.proceed();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object username = authentication.getPrincipal();

        User user = userMapper.getUserByUsername((String) username);
        operationLog.setUserId(user.getId());
        operationLog.setUsername(user.getUsername());
        operationLog.setContent(proceed.toString());
        operationLog.setId(KsuidGenerator.generate());
        operationLogMapper.insert(operationLog);
        return proceed;
    }

    @AfterThrowing(value = "operationLogPointcut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        OperationLog operationLog = new OperationLog();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object username = authentication.getPrincipal();

        User user = userMapper.getUserByUsername((String) username);
        operationLog.setId(KsuidGenerator.generate());
        operationLog.setUserId(user.getId());
        operationLog.setUsername(user.getUsername());
        // 设置异常信息
        operationLog.setContent(ex.getCause().getMessage());

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        operationLog.setRequestUrl(request.getRequestURI());
        operationLog.setLastLoginIp(IpUtils.getIpAddress(request));

        operationLogMapper.insert(operationLog);

        throw new BizException(ex.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

    }

}
