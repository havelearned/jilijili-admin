package wang.jilijili.music;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author admin
 */
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "wang.jilijili")
public class JilijiliMusicApplication {
    static PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        JilijiliMusicApplication.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(JilijiliMusicApplication.class, args);

        log.info("encode=>{}", passwordEncoder.encode("123456"));
    }

}
