package top.jilijili.system.amqp;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.jilijili.module.pojo.entity.sys.SysNotify;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.vo.sys.SysNotifyVo;
import top.jilijili.system.mapper.ConvertMapper;

import java.util.List;
import java.util.Objects;

import static top.jilijili.system.common.utils.KeyConstants.SYS_NOTIFY_ALL_SCHEDULED_QUEUE;
import static top.jilijili.system.common.utils.KeyConstants.SYS_NOTIFY_QUEUE;

/**
 * @author Amani
 * @date 2023年09月10日 9:35
 */
@Slf4j
@Component
@AllArgsConstructor
public class RabbitMQConsumer {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private  final ConvertMapper convertMapper;

    /**
     * 通知队列消费
     *
     * @param message
     */
    @RabbitListener(queues = SYS_NOTIFY_QUEUE)
    public void notifyConsumer(String message) {
        if (Objects.nonNull(message) && StringUtils.hasText(message)) {
            List<SysNotify> sysNotifies = JSON.parseArray(message, SysNotify.class);
            SysNotifyVo notifyVo;
            for (SysNotify notify : sysNotifies) {
                notifyVo = this.convertMapper.toNotifyVo(notify);
                // 发送指定用户
                if (notify.getReceiverId() != null) {
                    this.simpMessagingTemplate
                            .convertAndSendToUser(String.valueOf(notify.getReceiverId()), "/queue/notify", Result.ok(notifyVo));

                    // 发送全体用户
                } else {
                    this.simpMessagingTemplate
                            .convertAndSend("/queue/notify", Result.ok(notifyVo));
                }
            }
        }
        // 发送到web Socket
        log.info("消费了消息=>{}", message);
    }


    /**
     * 定时任务队列消费
     *
     * @param message
     */
    @RabbitListener(queues = SYS_NOTIFY_ALL_SCHEDULED_QUEUE)
    public void scheduledConsumer(String message) {
        // 设置定时定时任务
        // 发送到web Socket
        log.info("定时任务,消费了消息 =>{}", message);
    }
}
