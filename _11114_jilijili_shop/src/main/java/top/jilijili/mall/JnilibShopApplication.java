package top.jilijili.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = {"top.jilijili.common.*","top.jilijili.mall.*"})
public class JnilibShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(JnilibShopApplication.class, args);

    }
}