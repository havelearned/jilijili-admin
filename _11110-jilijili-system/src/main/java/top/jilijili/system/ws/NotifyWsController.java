package top.jilijili.system.ws;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import top.jilijili.module.entity.SysNotify;

import java.util.Objects;

/**
 * webSocket 系统通知
 *
 * @author Amani
 * @date 2023年10月13日 23:51
 */
@RestController
@Slf4j
@AllArgsConstructor
public class NotifyWsController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/notify")
    public void handleAudioMessage(SysNotify sysNotify) {
        log.warn("websocket,{/notify} 接收到了数据:{}", sysNotify);
        // 发送给指定用户
        if (Objects.nonNull(sysNotify.getReceiverId())) {
//            this.simpMessagingTemplate.convertAndSend("/topic/1024", webRTCDto);
        }
        // 发送给全体用户
        this.simpMessagingTemplate.convertAndSend("/notify", sysNotify);
    }


}
