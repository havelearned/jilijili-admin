package wang.jilijili.common.aspect;

import com.github.ksuid.KsuidGenerator;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wang.jilijili.common.annotation.NoRepeatSubmit;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.service.RedisService;
import wang.jilijili.common.utils.IpUtils;

import java.lang.reflect.Method;

import static wang.jilijili.common.constant.RedisKeyConstant.KEY_NO_REPEAT_SUBMIT;


/**
 * 表单切面类
 *
 * @author Amani
 * @date 2023年04月03日 9:58
 */
@Slf4j
@Aspect
@Component
public class RepastSubmitAspect {

    @Resource
    RedisService redisService;

    @Pointcut(value = "@annotation(wang.jilijili.common.annotation.NoRepeatSubmit)")
    public void noRepeatSubmitPointcut() {
    }

    @Around("noRepeatSubmitPointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        // 获取客户端请求的ip地址
        String ip = IpUtils.getIpAddress(request);

        // 获取到注解信息
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 获取目标类，方法
        Method method = signature.getMethod();
        String name = method.getName();
        // 获取类名称
        String className = method.getDeclaringClass().getName();
        String ipKey = className + "#" + name;
        log.debug("类名与方法名的hashCode值：" + ipKey.hashCode());
        int keyHashCode = Math.abs(ipKey.hashCode());
        String key = KEY_NO_REPEAT_SUBMIT + ip + "-" + keyHashCode;
        log.info("ipKey={},keyHashCode={},key={}", ipKey, keyHashCode, key);

        // 得到注解
        NoRepeatSubmit annotation = method.getAnnotation(NoRepeatSubmit.class);
        long timeout = annotation.timeout();
        String value = (String) redisService.get(key);
        if (StringUtils.isNotBlank(value)) {
            redisService.del(key);
            return Result.ok(null);
        }

        redisService.set(key, KsuidGenerator.generate(), timeout);
        return point.proceed();
    }
}
