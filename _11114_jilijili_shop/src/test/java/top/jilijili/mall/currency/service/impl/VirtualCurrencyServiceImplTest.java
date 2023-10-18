package top.jilijili.mall.currency.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.mall.currency.service.VirtualCurrencyService;
import top.jilijili.module.pojo.dto.currency.VirtualCurrencyDto;
import top.jilijili.module.pojo.vo.currency.VirtualCurrencyVo;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class VirtualCurrencyServiceImplTest {

    @Autowired
    VirtualCurrencyService service;

    @Test
    public void selectAll() {
        VirtualCurrencyDto built = VirtualCurrencyDto.builder()
                .page(1)
                .size(10)
                .build();

        Page<VirtualCurrencyVo> page = service.selectAll(built);
        System.out.println("================================================================================================>");
        System.out.println(JSON.toJSONString(page));
    }
}