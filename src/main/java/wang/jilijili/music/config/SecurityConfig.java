package wang.jilijili.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import wang.jilijili.music.exception.RestAuthenticationEntryPoint;
import wang.jilijili.music.filter.JwtAuthenticationFilter;
import wang.jilijili.music.filter.JwtAuthorizationFilter;
import wang.jilijili.music.handler.RestAccessDeniedHandler;
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

    UserService userService;

    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    RestAccessDeniedHandler restAccessDeniedHandler;

    public SecurityConfig(UserService userService, RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                          RestAccessDeniedHandler restAccessDeniedHandler) {
        this.userService = userService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                // 允许所有post请求访问,其他都需要鉴权后访问
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL,"/test/*").permitAll()
//                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()

                //添加过滤器, 不通过Session获取SecurityContext
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) //鉴权过滤器
                .addFilter(new JwtAuthorizationFilter(authenticationManager())) // 授权过滤器
                .exceptionHandling() // 异常处理
                .authenticationEntryPoint(this.restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


    }


}
