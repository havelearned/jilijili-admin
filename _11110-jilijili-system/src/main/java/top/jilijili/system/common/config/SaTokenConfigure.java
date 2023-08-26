package top.jilijili.system.common.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.jilijili.system.entity.vo.SysRoleVo;
import top.jilijili.system.mapper.SysUserMapper;

import java.util.List;

/**
 * @author Amani
 * @date 2023年06月28日 14:26
 */
@Configuration

public class SaTokenConfigure implements WebMvcConfigurer, StpInterface {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // sessionid 多次访问一致
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        // 允许任何域访问
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


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        SaInterceptor saInterceptor = new SaInterceptor();

        registry.addInterceptor(saInterceptor)
                .addPathPatterns("/**");
//                .excludePathPatterns("/test/**","/sysUser/login")
//                .addPathPatterns("/sysUser/**");
    }


    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        SysUserMapper sysUserMapper = SpringUtil.getBean(SysUserMapper.class);
        List<SysRoleVo> userRoles = sysUserMapper.queryRoleByUserId((Long.valueOf((String) loginId)));
        if (userRoles.isEmpty()) {
            return null;
        }
        return userRoles.stream().map(SysRoleVo::getRoleName).toList();
    }

}
