package wang.jilijili.common.aspect;


import com.alibaba.fastjson2.JSONObject;
import com.github.ksuid.KsuidGenerator;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
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

    @Pointcut(value = "@annotation(wang.jilijili.common.annotation.JilJilOperationLog)")
    public void operationLogPointcut() {
    }


    @Around(value = "operationLogPointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
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

        // HACK 确保只调用一次pjp.proceed(),多次调用多次执行目标方法
        result = pjp.proceed();

        // 设置返回值
        operationLog.setContent(JSONObject.toJSONString(result));
        this.operationLogMapper.insert(operationLog);
        return result;
    }

}
