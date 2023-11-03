package top.jilijili.system.common.aspect;

import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.jilijili.system.common.heandler.JiliException;
import top.jilijili.system.common.utils.IpUtils;
import top.jilijili.system.common.utils.KeyConstants;
import top.jilijili.common.entity.Result;

import java.util.concurrent.TimeUnit;

/**
 * @author Amani
 * @date 2023年06月29日 21:47
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class LoginAspect {

    private RedisTemplate<String, Object> redisTemplate;

    // 切入点
    @Pointcut("@annotation(top.jilijili.system.common.aspect.annotation.RestrictAccess)")
    public void loginPointCut() {
    }


    /**
     * 登录接口限制
     *
     * @param pjp
     * @return
     */
    @Around(value = "loginPointCut()")
    public Object loginBefore(ProceedingJoinPoint pjp) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String ipSource = IpUtils.getIpSource(IpUtils.getIpAddress(request));


        Result result;
        try {
            result = JSONUtil.toBean(JSONUtil.toJsonStr(pjp.proceed()), Result.class);
        } catch (Throwable e) {
            throw new JiliException(e.getMessage());
        }

        String key = KeyConstants.LOGIN_RESTRICTION + ipSource;
        int loginAttempts = redisTemplate.opsForValue().get(key) != null
                ? (int) redisTemplate.opsForValue().get(key) : 0;

        // TODO 限制登录次数放到配置文件中
        int MAX_LOGIN_ATTEMPTS = 5;
        if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
            return Result.fail("登录错误次数过多,请一小时后再次尝试");
        }

        if (result.getFlag()) {
            redisTemplate.delete(key);
        } else {
            // 登录失败，增加登录尝试次数
            redisTemplate.opsForValue().increment(key, 1);
            // 设置过期时间，例如锁定账户一段时间
            redisTemplate.expire(key, 1, TimeUnit.HOURS);

        }
        return result;
    }
}
