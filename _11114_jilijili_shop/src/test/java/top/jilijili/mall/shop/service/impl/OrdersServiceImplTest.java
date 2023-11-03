package top.jilijili.mall.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.mall.shop.service.OrdersService;
import top.jilijili.module.pojo.vo.shop.OrdersVo;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrdersServiceImplTest {

    @Autowired
    OrdersService ordersService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void getValue(){
        OrdersVo ordersVo = (OrdersVo) redisTemplate.opsForValue().get("test:orderList3");
        System.out.println(ordersVo);

    }

    @Test
    public void getOrderList() {
        OrdersVo ordersVo = new OrdersVo();
        ordersVo.setOrderId("123123");
        ordersVo.setTotalAmount(new BigDecimal(2300));
        ordersVo.setOrderDate(new Date());
        ordersVo.setOrderStatus(0);
        redisTemplate.opsForValue().set("test:orderList3",ordersVo);
    }

    @Test
    public void getOrderInfoById() {
    }

    @Test
    public void getOrderListByUserId() {
    }

    @Test
    public void queryOrdersTodayData() {
    }
}