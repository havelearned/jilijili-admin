package top.jilijili.system.pattern.impl;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.SysNotify;
import top.jilijili.system.pattern.PublishMode;

import java.util.List;

import static top.jilijili.system.common.utils.KeyConstants.NOTIFY_EXCHANGE;
import static top.jilijili.system.common.utils.KeyConstants.SYS_ROUTER;

/**
 * 实现立即发布的逻辑
 */
@Component
@AllArgsConstructor
public class ImmediatePublishMode implements PublishMode {
    private RabbitTemplate rabbitTemplate;

    @Override
    public void handlePublish() {
        System.out.println("立即发布");
    }

    /**
     * 发布并立即添加到数据
     *
     * @param sysNotifyList
     */
    @Override
    public void handlePublish(List<SysNotify> sysNotifyList) {
        // 发送通知
        this.rabbitTemplate.convertAndSend(NOTIFY_EXCHANGE, SYS_ROUTER, JSON.toJSONString(sysNotifyList));

    }
}