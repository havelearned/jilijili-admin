package top.jilijili.shop;

import com.alibaba.fastjson2.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.shop.entity.vo.CartsVo;
import top.jilijili.shop.service.CartsService;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShropTest {

    @Autowired
    CartsService cartsService;


    @Test
    public void _01() {
        List<CartsVo> list = cartsService.queryCartsByUserIdList(101);

        System.out.println("========================================");
        System.out.println(JSON.toJSONString(list));
        System.out.println("========================================");


    }

}