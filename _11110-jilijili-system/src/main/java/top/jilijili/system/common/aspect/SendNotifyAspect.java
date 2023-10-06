package top.jilijili.system.common.aspect;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.vo.Result;

import static top.jilijili.system.common.utils.KeyConstants.NOTIFY_EXCHANGE;
import static top.jilijili.system.common.utils.KeyConstants.SYS_ROUTER;

/**
 * 发送通知的切面
 *
 * @author Amani
 * @date 2023年09月10日 9:00
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class SendNotifyAspect {

    private RabbitTemplate rabbitTemplate;

    // 切入点
    @Pointcut("@annotation(top.jilijili.system.common.aspect.annotation.SysNotify)")
    public void sendNotifyPointCut() {
    }

    @AfterReturning(pointcut = "sendNotifyPointCut()", returning = "result")
    public void afterNotify(JoinPoint joinPoint, Result<?> result) {
        String methodName = joinPoint.getSignature().getName();
        this.rabbitTemplate.convertAndSend(NOTIFY_EXCHANGE, SYS_ROUTER, JSON.toJSONString(result));
        log.info("发送系统通知:{}\n执行目标未知:{}", result.toString(), methodName);

    }


}
