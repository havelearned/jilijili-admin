package top.jilijili.system.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import top.jilijili.system.common.config.MinioConfig;

/**
 * @author Amani
 * @date 2023年06月28日 14:23
 */
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String webSocketTest(String param) {
        simpMessagingTemplate.convertAndSend("/topic/greetings", param);
        return "param";
    }


    @GetMapping("/03")
    public String minioInfo() {

        return MinioConfig.minioClient.toString();
    }


    @GetMapping("/02")
    public String test_02(@CookieValue(value = "jilijili-token", defaultValue = "没有获取到CooKie") String token,
                          @CookieValue(value = "userinfo", defaultValue = "没有获取到CooKie") String userinfo) {
        return String.format("""
                 %s
                 %s
                """, token, userinfo);
    }

    @SaCheckRole("SYS_ADMIN")
    @GetMapping("/01")
    public String test_01() {
        return "必须的登陆才能访问";
    }
}
