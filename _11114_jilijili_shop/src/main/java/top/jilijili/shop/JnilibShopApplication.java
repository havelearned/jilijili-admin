package top.jilijili.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = {"top.jilijili.common.*","top.jilijili.shop.*"})
public class JnilibShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(JnilibShopApplication.class, args);

    }
}
