package wang.jilijili.music.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import wang.jilijili.music.filter.JwtAuthenticationFilter;
import wang.jilijili.music.filter.JwtAuthorizationFilter;
import wang.jilijili.music.handler.RestAuthenticationHandler;
import wang.jilijili.music.service.UserService;

/**
 * @Auther: Amani
 * @Date: 2023/1/28 16:12
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String SECRET = "Jilijili-Music"; // 密钥
    public static final long EXPIRATION_TIME = 864000000;//10days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/";

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Autowired
    UserService userService;
    @Autowired
    RestAuthenticationHandler authenticationHandler;


    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     */


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                // 允许所有post请求访问,其他都需要鉴权后访问
                .authorizeRequests()
//                .antMatchers("/test/*", "/users/*").permitAll()
                .antMatchers(HttpMethod.POST, "/login", "/logout").permitAll()
                .anyRequest().authenticated()
                .and()

                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .addFilterAt(new JwtAuthenticationFilter(authenticationManager(), authenticationHandler),
                        UsernamePasswordAuthenticationFilter.class)
                .logout().logoutUrl("/logout").logoutSuccessHandler(authenticationHandler)
                .and()


                //添加过滤器, 不通过Session获取SecurityContext
//                .addFilter(new JwtAuthenticationFilter(authenticationManager())) //  鉴权过滤器
//                .addFilter(new JwtAuthorizationFilter(authenticationManager())) // 授权过滤器

                // 异常处理
                .exceptionHandling()
                .accessDeniedHandler(authenticationHandler)
                .authenticationEntryPoint(authenticationHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .maximumSessions(-1);


    }


}
