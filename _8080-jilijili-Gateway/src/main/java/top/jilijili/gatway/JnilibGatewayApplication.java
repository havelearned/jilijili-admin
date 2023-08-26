package top.jilijili.gatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "top.jilijili.gatway.*")
public class JnilibGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(JnilibGatewayApplication.class, args);
    }

}