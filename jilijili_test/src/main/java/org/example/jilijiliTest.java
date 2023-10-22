package org.example;

import org.example.mapper.ProductsMapper;
import org.example.mapper.SysUserMapper;
import org.example.mapper.UserRatingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import top.jilijili.module.pojo.entity.shop.Products;
import top.jilijili.module.pojo.entity.shop.UserRatings;
import top.jilijili.module.pojo.entity.sys.SysUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
@SpringBootApplication
public class jilijiliTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    ProductsMapper productsMapper;
    @Autowired
    UserRatingsMapper userRatingsMapper;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(jilijiliTest.class, args);
        jilijiliTest bean = context.getBean(jilijiliTest.class);
        bean.query();

    }

    private void query() {


        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        List<Products> products = productsMapper.selectList(null);
        List<Long> productIds = products.stream().map(Products::getProductId).toList();


        ExecutorService executorService = Executors.newFixedThreadPool(10); // 创建一个包含10个线程的线程池


        List<UserRatings> userRatings = new ArrayList<>();

        Random random = new Random();
        for (SysUser sysUser : sysUsers) {
            for (Long productId : productIds) {
                executorService.execute(() -> {
                    BigDecimal bigDecimal = BigDecimal.valueOf(Math.random() * (9.55f - 1.00f) + 1.00f);

                    UserRatings o = UserRatings.builder()
                            .userId(String.valueOf(sysUser.getUserId()))
                            .itemId(String.valueOf(productId))
                            .rating(bigDecimal)
                            .timestamp(System.currentTimeMillis())
                            .build();

                    System.out.println("插入数据:=>" + o.toString());
                    userRatingsMapper.insert(o);
                });
            }
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            // 等待所有任务执行完成
        }

        System.out.println("执行完成");

    }


}
