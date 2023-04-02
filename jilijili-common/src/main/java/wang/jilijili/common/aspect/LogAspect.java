package wang.jilijili.common.aspect;


import com.alibaba.fastjson2.JSONObject;
import com.github.ksuid.KsuidGenerator;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.jilijili.common.annotation.JilJilOperationLog;
import wang.jilijili.common.core.mapper.OperationLogMapper;
import wang.jilijili.common.core.mapper.UserMapper;
import wang.jilijili.common.core.pojo.entity.OperationLog;
import wang.jilijili.common.core.pojo.entity.User;
import wang.jilijili.common.utils.IpUtils;

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

    @Resource
    OperationLogMapper operationLogMapper;

    @Resource
    UserMapper userMapper;

    private static final ThreadLocal<OperationLog> OPERATION_LOG_THREAD_LOCAL = new InheritableThreadLocal<>();

    @Pointcut(value = "@annotation(wang.jilijili.common.annotation.JilJilOperationLog)")
    public void operationLogPointcut() {
    }

    @After(value = "operationLogPointcut()")
    public void after(JoinPoint pjp) {
        log.info("wang.jilijili.common.aspect.LogAspect.@after===>被执行了");
        OperationLog operationLog = OPERATION_LOG_THREAD_LOCAL.get();
        if (Objects.isNull(operationLog)) {
            return;
        }
//        this.operationLogMapper.insert(operationLog);

    }

    @Around(value = "operationLogPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("wang.jilijili.common.aspect.LogAspect.@afterReturning===>被执行了");
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        OperationLog operationLog = new OperationLog();
        JilJilOperationLog annotation = method.getAnnotation(JilJilOperationLog.class);
        if (annotation != null) {
            operationLog.setModuleName(annotation.moduleName());
            operationLog.setOperationType(annotation.type().getNum());
        }
        operationLog.setMethodExecution(pjp.getTarget().getClass().getName() + "." + method.getName());
        operationLog.setRequestData(Arrays.toString(pjp.getArgs()));

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        operationLog.setRequestUrl(request.getMethod() + " " + request.getRequestURI());
        operationLog.setLastLoginIp(IpUtils.getIpAddress(request));


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object username = authentication.getPrincipal();

        User user = userMapper.getUserByUsername((String) username);
        // 设置id
        operationLog.setUserId(user.getId());
        operationLog.setUsername(user.getUsername());
        operationLog.setId(KsuidGenerator.generate());


        // 设置返回值
        operationLog.setContent(JSONObject.toJSONString(pjp.proceed()));

        OPERATION_LOG_THREAD_LOCAL.set(operationLog);
        OPERATION_LOG_THREAD_LOCAL.remove();
        this.operationLogMapper.insert(operationLog);
        return pjp.proceed();
    }

}
