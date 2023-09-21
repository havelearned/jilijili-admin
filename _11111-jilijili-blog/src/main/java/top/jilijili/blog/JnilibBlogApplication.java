package top.jilijili.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 博客服务
 *
 * @author Amani
 * @date 2023年08月13日 23:06
 */
@SpringBootApplication
@ComponentScan(basePackages = {"top.jilijili.blog.*", "top.jilijili.common"})
public class JnilibBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(JnilibBlogApplication.class, args);
    }
}
