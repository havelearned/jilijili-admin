package wang.jilijili.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author admin
 */
@Configuration
public class CrossOriginConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // sessionid 多次访问一致
        corsConfiguration.setAllowCredentials(true);

        corsConfiguration.addAllowedOriginPattern("*");

        // 允许任何头
        corsConfiguration.addAllowedHeader("*");

        // 允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");

        // 暴露哪些头部信息 不能用*因为跨域访问默认不能获取全部头部信息
        corsConfiguration.addExposedHeader("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
