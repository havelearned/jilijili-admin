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


    /**
     * 创建队列 系统队列
     */
    @Bean
    public Queue sysNotifyQueue() {
        return new Queue(SYS_NOTIFY_QUEUE);
    }

    /**
     * 通知全体队列
     *
     * @return
     */
    @Bean
    public Queue sysAllNotifyQueue() {
        return new Queue(SYS_NOTIFY_ALL_QUEUE);
    }

    /**
     * 定时通知队列
     *
     * @return
     */
    @Bean
    public Queue sysAllNotifyQueueSchedule() {
        return new Queue(SYS_NOTIFY_ALL_SCHEDULED_QUEUE);
    }


    /**
     * 绑定通知队列
     */
    @Bean
    public Binding binding(Queue sysNotifyQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(sysNotifyQueue).to(directExchange).with(SYS_ROUTER);
    }

    /**
     * 绑定通知全体队列
     *
     * @return
     */
    @Bean
    public Binding bindingALlNotifyQueue(Queue sysAllNotifyQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(sysAllNotifyQueue).to(directExchange).with(SYS_ROUTER_NOTIFY_ALL);
    }

    /**
     * 绑定定时通知队列
     */
    @Bean
    public Binding bindingAllNotifyQueueSchedule(Queue sysAllNotifyQueueSchedule, DirectExchange directExchange) {
        return BindingBuilder.bind(sysAllNotifyQueueSchedule).to(directExchange).with(SYS_ROUTER_NOTIFY_SCHEDULED);
    }


}
