package top.jilijili.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 启动类
 */
@EnableFeignClients
@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {
        "top.jilijili.comment.recommendation",
        "top.jilijili.common.*",
        "top.jilijili.mall.*"})
public class JnilibShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(JnilibShopApplication.class, args);

    }

    @Bean(name = "secKillThreadPool")
    public ExecutorService  customTaskExecutor() {
        ExecutorService executorService = Executors.newFixedThreadPool(12);

        return executorService;
    }
}
