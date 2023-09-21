package top.jilijili.shop;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.module.entity.dto.ProductsDto;
import top.jilijili.module.entity.vo.CartsVo;
import top.jilijili.shop.mapper.OrdersMapper;
import top.jilijili.shop.mapper.ProductsMapper;
import top.jilijili.shop.service.CartsService;
import top.jilijili.shop.service.ProductsService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ShropTest {

    @Autowired
    CartsService cartsService;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    ProductsMapper productsMapper;

    @Autowired
    ProductsService productsService;

    @Test
    public void _02() {
        ProductsDto productsDto = new ProductsDto();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_YEAR, -60);
        productsDto.setComparisonTime(new Date());
        productsDto.setCreatedTime(instance.getTime());

        Map<String, Object> stringObjectMap = this.productsService.queryProductsTodayInfo(productsDto);

        System.out.println("====================================================================>");
        System.out.println(JSON.toJSONString(stringObjectMap));
        System.out.println("====================================================================>");

    }


    @Test
    public void _01() {
        List<CartsVo> list = cartsService.queryCartsByUserIdList(101);

        System.out.println("========================================");
        System.out.println(JSON.toJSONString(list));
        System.out.println("========================================");


    }

}