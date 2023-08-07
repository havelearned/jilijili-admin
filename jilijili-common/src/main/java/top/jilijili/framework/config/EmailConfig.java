package top.jilijili.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @author Amani
 * @date 2023年06月01日 21:54
 */
@Component
public class EmailConfig {

    @Bean(value = "mailSender")
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("1918652173@qq.com");
        javaMailSender.setPassword("zyilpukalydybaee");
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }

}
