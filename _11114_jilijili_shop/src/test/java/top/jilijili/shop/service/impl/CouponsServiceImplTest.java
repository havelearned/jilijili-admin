package top.jilijili.shop.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.dto.shop.CouponsDto;
import top.jilijili.module.pojo.dto.sys.SysUserDto;
import top.jilijili.module.pojo.vo.shop.UserWithCouponsVo;
import top.jilijili.mall.shop.service.CouponsService;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CouponsServiceImplTest {

    @Autowired
    CouponsService couponsService;

    @Test
    public void selectAllCoupons() {
        SysUserDto build1 = SysUserDto.builder()
                .userId(19688L)
                .build();
        CouponsDto build = CouponsDto.builder()
                .page(1)
                .size(10)
                .sysUserDto(build1)
                .build();
        Result<IPage<UserWithCouponsVo>> iPageResult = this.couponsService.selectAllUserCoupons(build);
        System.out.println("========================================================================================");
        System.out.println(JSONObject.toJSONString(iPageResult));
    }

    @Test
    public void selectOneCoupons() {
    }

    @Test
    public void insertCoupons() {
    }

    @Test
    public void updateCoupons() {
    }
}