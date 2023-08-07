package top.jilijili.system.ws;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jilijili.system.entity.dto.CmRecordDto;
import top.jilijili.system.entity.dto.SysUserDto;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.service.CmRecordService;

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


    @SubscribeMapping("/user/{id}/listener")
    public void subscribeMapping(@DestinationVariable("id") final String id) {
        // 订阅初始化操作，例如向订阅用户发送欢迎消息等
        String message = "欢迎订阅消息，用户: " + id;
        this.simpMessagingTemplate.convertAndSendToUser(id, "/listener", message);
    }



    @MessageMapping("/chat")
    public Result<Boolean> handleChatMessage(@Payload CmRecordDto cmRecordDto) {
        log.warn("{} 对 {} 说=> {}", cmRecordDto.getSenderId(), cmRecordDto.getReceiverId(), cmRecordDto.getMessage());
        this.simpMessagingTemplate.convertAndSendToUser(cmRecordDto.getReceiverId(), "/queue/message", cmRecordDto.getMessage());
        boolean save = true;
        return Result.ok(save);
    }

    @MessageMapping("/sendMessage")
    public Result<?> handleMessage(@Payload SysUserDto userDto, SimpMessageHeaderAccessor headerAccessor) {
        log.info("客户端{}: {}", headerAccessor.getSessionId(), userDto);
        return Result.ok("done");
    }
}
