package top.jilijili.mall.seckill.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SeckillServiceTest {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void processSeckillRequest() {
        redisTemplate.opsForValue().set("stock_count",10);
        System.out.println(redisTemplate.opsForValue().get("stock_count"));
    }
}