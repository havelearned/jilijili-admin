package wang.jilijili.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wang.jilijili.music.filter.JwtAuthorizationFilter;
import wang.jilijili.music.filter.LoginAuthenticationFilter;
import wang.jilijili.music.handler.RestAuthenticationHandler;
import wang.jilijili.music.service.impl.UserServiceImpl;

/**
 * @Auther: Amani
 * @Date: 2023/1/28 16:12
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static final String SECRET = "Jilijili-Music"; // 密钥
    public static final long EXPIRATION_TIME = 864000000;//10days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/";


    /**
     * 允许抛出用户不存在的异常
     *
     * @param userService myUserDetailsService
     * @return DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserServiceImpl userService,
                                                               PasswordEncoder passwordEncoder) {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsPasswordService(userService);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }


    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     */

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                            LoginAuthenticationFilter loginAuthenticationFilter,
                                            JwtAuthorizationFilter jwtAuthorizationFilter,
                                            RestAuthenticationHandler authenticationHandler) throws Exception {


        httpSecurity
                .csrf().disable().cors().disable()
                .httpBasic().and()
                // 请求白名单
                .authorizeHttpRequests()
                .requestMatchers("/error", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()

                .addFilterAt(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilter(jwtAuthorizationFilter)
                .logout().logoutUrl("/logout").logoutSuccessHandler(authenticationHandler)
                .and()

                // 异常处理
                .exceptionHandling()
                .accessDeniedHandler(authenticationHandler)
                .authenticationEntryPoint(authenticationHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .maximumSessions(-1);
        return httpSecurity.build();

    }


}
