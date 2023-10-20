package top.jilijili.mall.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.module.pojo.dto.shop.ProductsDto;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ProductsServiceImplTest {

    @Autowired
    private ProductsServiceImpl productsService;


    @Test
    public void queryProductListV2() {
        ProductsDto productsDto = new ProductsDto();
        productsDto.setPage(1);
        productsDto.setPage(10);
    }
}