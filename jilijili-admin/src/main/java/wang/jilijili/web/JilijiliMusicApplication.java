package wang.jilijili.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author admin
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "wang.jilijili")
public class JilijiliMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(JilijiliMusicApplication.class, args);
    }

}
