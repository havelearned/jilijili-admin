package wang.jilijili.web;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
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
@EnableAdminServer
public class JilijiliMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(JilijiliMusicApplication.class, args);
    }

}
