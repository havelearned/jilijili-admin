package top.jilijili.system.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static top.jilijili.system.common.utils.KeyConstants.*;

/**
 * RabbitMQ 配置类
 *
 * @author Amani
 * @date 2023年09月10日 9:20
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    public Queue sysNotifyQueue() {
        return new Queue(SYS_NOTIFY_QUEUE);
    }

    /**
     * 创建交换机
     * 持久化
     * 自动删除
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(NOTIFY_EXCHANGE, true, true);
    }

    @Bean
    public Binding binding(Queue sysNotifyQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(sysNotifyQueue).to(directExchange).with(SYS_ROUTER);
    }
}
