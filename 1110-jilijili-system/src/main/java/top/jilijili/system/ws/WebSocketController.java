package top.jilijili.system.ws;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.BinaryMessage;
import top.jilijili.system.entity.dto.CmRecordDto;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.CmRecordService;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author Amani
 * @date 2023年08月06日 8:45
 */
@RestController
@Slf4j
@AllArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final CmRecordService cmRecordService;
    private final ConvertMapper convertMapper;

    @MessageMapping("/audio")
    public void handleAudioMessage(String message) {
        log.warn("接收到了数据:{}", message);
        // 获取可读取数量
    }

    /**
     * 语音通话
     */
    @MessageMapping("/audio/{sendId}/{receiverId}")
    public void handleAudioMessage(@DestinationVariable("sendId") String sendId,
                                   @DestinationVariable("receiverId") String receiverId,
                                   BinaryMessage binaryBody) throws IOException {

        log.warn("{} 对 {} 发起了音频通话,内容大小=>{}", sendId, receiverId, binaryBody.getPayload().array().length);

        // /user/1/queue/audioMessage
        this.simpMessagingTemplate.convertAndSend("/user/" + receiverId + "/queue/audioMessage", ByteBuffer.wrap(binaryBody.getPayload().array()));

    }

    /**
     * 在线文本聊天
     *
     * @param cmRecordDto 聊天记录
     * @return
     */
    @MessageMapping("/chat")
    public Result<Boolean> handleChatMessage(@Payload CmRecordDto cmRecordDto) {
        log.warn("{} 对 {} 说=> {}", cmRecordDto.getSenderId(), cmRecordDto.getReceiverId(), cmRecordDto.getMessage());
        this.simpMessagingTemplate.convertAndSendToUser(cmRecordDto.getReceiverId(), "/queue/message", Result.ok(cmRecordDto));
        boolean save = true;
        return Result.ok(save);
    }


}
