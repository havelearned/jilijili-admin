package wang.jilijili.cloud.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer //开启AdminServer的支持
@SpringBootApplication
public class AdminServerJnilibCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerJnilibCloudApplication.class, args);
	}

}
