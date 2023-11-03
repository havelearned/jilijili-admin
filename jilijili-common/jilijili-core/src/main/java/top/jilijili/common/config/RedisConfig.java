package top.jilijili.common.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author Amani
 * @date 2023年06月24日 14:09
 */
@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置Key的序列化器为String序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置Value的序列化器为Jackson序列化器，并传入自定义的ObjectMapper对象
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        //hash类型key序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //hash类型value序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    /**
     * 配置缓存,默认5分钟
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration
                .defaultCacheConfig().entryTtl(Duration.ofMinutes(5))) // 设置缓存时间
                .transactionAware()
                .build();
    }

}
