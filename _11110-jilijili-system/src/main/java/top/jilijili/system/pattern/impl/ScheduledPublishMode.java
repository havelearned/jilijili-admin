package top.jilijili.system.pattern.impl;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.SysNotify;
import top.jilijili.system.common.utils.KeyConstants;
import top.jilijili.system.pattern.PublishMode;

import java.util.List;

/**
 * 实现定时发布
 */
@Component
@AllArgsConstructor
public class ScheduledPublishMode implements PublishMode {
    private RabbitTemplate rabbitTemplate;

    @Override
    public void handlePublish() {
        System.out.println("定时发布");
    }

    @Override
    public void handlePublish(List<SysNotify> sysNotifyList) {
        // 发送到定时任务队列
        this.rabbitTemplate.convertAndSend(KeyConstants.NOTIFY_EXCHANGE, KeyConstants.SYS_ROUTER_NOTIFY_SCHEDULED,
                JSON.toJSONString(sysNotifyList));

    }
}