package top.jilijili.system.ws;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import top.jilijili.module.pojo.dto.shop.CmRecordDto;
import top.jilijili.common.entity.Result;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.CmRecordService;

/**
 * @author Amani
 * @date 2023年08月06日 8:45
 */
@RestController
@Slf4j
@AllArgsConstructor
public class ChatWsController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final CmRecordService cmRecordService;
    private final ConvertMapper convertMapper;

    @MessageMapping("/test")
    public void testMessage(String string) {
        log.info("webSocket,testMessage=>{}", string);

        // 前端订阅: /queue/message 或者 /queue/*
        this.simpMessagingTemplate.convertAndSend("/queue/message", "收到了");

    }

    /**
     * 在线文本聊天
     *
     * @param cmRecordDto 聊天记录
     * @return
     */
    @MessageMapping("/chat")
    public void handleChatMessage(CmRecordDto cmRecordDto) {

        log.warn("{} 对 {} 说=> {}", cmRecordDto.getSenderId(), cmRecordDto.getReceiverId(), cmRecordDto.getMessage());

        // 前端订阅: /user/${receiverId}/queue/message 或者 /user/${receiverId}/queue/*
        this.simpMessagingTemplate.convertAndSendToUser(cmRecordDto.getReceiverId(), "/queue/message", Result.ok(cmRecordDto));

    }
}
