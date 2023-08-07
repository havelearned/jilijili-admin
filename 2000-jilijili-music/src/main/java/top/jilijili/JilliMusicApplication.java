package top.jilijili;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Amani
 * @date 2023年06月22日 0:27
 */

@SpringBootApplication
@ComponentScan(basePackages = "top.jilijili.music.*")
public class JilliMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(JilliMusicApplication.class, args);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

}
