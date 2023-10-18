package top.jilijili.mall.shop.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.shop.service.CategoriesService;
import top.jilijili.module.pojo.dto.shop.CategoriesDto;
import top.jilijili.module.pojo.entity.shop.Categories;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CategoriesServiceImplTest {

    @Autowired
    private CategoriesService categoriesService;

    @Test
    public void getCategoriesListDict() {
        CategoriesDto build = CategoriesDto.builder()
                .page(1)
                .size(1000)
                .build();
        Result<Page<Categories>> list = categoriesService.getCategoriesList(build);
        System.out.println("================================================================");
        System.out.println(JSON.toJSONString(list));
    }
}