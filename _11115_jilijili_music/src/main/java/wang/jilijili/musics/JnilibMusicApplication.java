package wang.jilijili.musics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 */
@ComponentScan(basePackages = {
        "top.jilijili.common.*",
        "wang.jilijili.musics.*",
})
@SpringBootApplication
public class JnilibMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(JnilibMusicApplication.class, args);

    }
}
