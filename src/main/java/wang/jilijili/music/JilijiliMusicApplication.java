package wang.jilijili.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan(basePackages = "wang.jilijili.music.mapper.*")
public class JilijiliMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JilijiliMusicApplication.class, args);
    }

}
