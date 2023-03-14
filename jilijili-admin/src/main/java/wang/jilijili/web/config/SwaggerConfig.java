package wang.jilijili.web.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amani
 * @date 2023年02月12日 11:41
 */
@Configuration
public class SwaggerConfig {


    /**
     * 访问: http://localhost:8080/swagger-ui/index.html
     * */
    @Bean
    public OpenAPI restfulOpenApi() {
        return new OpenAPI()
                .info(new Info().title("肌理音乐接口文档")
                        .description("Zoo & Animal Detail APi")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Wiki Documentation")
                        .url("https://springdoc.org/v2"));
    }


}
