package top.jilijili.system.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static top.jilijili.system.common.utils.KeyConstants.SYS_NOTIFY_QUEUE;

/**
 * @author Amani
 * @date 2023年09月10日 9:35
 */
@Component
@Slf4j
public class RabbitMQConsumer {
    @RabbitListener(queues = SYS_NOTIFY_QUEUE)
    public void testConsumer(String message) {
        log.info("消费了消息=>{}", message);
    }
}
