package top.jilijili;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Amani
 * @date 2023年06月22日 0:27
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "top.jilijili.system.*")
public class JilliSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(JilliSystemApplication.class, args);
        log.warn("启动成功：Sa-Token配置如下:{}", SaManager.getConfig());

    }


}
