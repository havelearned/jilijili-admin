package top.jilijili.shop.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.module.entity.dto.PromocodesDto;
import top.jilijili.module.entity.vo.PromocodesVo;
import top.jilijili.mall.shop.service.PromocodesService;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class PromocodesServiceImplTest {

    @Autowired
    private PromocodesService promocodesService;


    @Test
    public void selectAllPromocodes() {
        PromocodesDto build = PromocodesDto.builder()
                .page(1)
                .size(20)
                .build();
        IPage<PromocodesVo> promocodesVoIPage = promocodesService.selectAllPromocodes(build);
        System.out.println("=======================================================>");
        System.out.println(JSONObject.toJSONString(promocodesVoIPage));
    }

    @Test
    public void selectOnePromocodes() {
    }

    @Test
    public void insertPromocodes() {
    }

    @Test
    public void updatePromocodes() {
    }

    @Test
    public void batchPromocodes() {
    }
}