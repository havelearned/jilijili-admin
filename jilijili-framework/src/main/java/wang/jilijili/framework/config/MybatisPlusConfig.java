package wang.jilijili.framework.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.jilijili.framework.handler.MpMetaObjectHandler;


/**
 * @author Amani
 * @date 2023年02月12日 22:08
 */
@Configuration
@MapperScan(basePackages = {
        "wang.jilijili.system.mapper",
        "wang.jilijili.music.mapper"
})
public class MybatisPlusConfig {

    @Autowired
    MpMetaObjectHandler mpMetaObjectHandler;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(mpMetaObjectHandler);
        return globalConfig;
    }

}
