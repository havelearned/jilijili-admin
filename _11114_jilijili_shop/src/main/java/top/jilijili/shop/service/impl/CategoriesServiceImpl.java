package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.shop.entity.Categories;
import top.jilijili.shop.service.CategoriesService;
import top.jilijili.shop.mapper.CategoriesMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【shop_categories(商品分类表)】的数据库操作Service实现
* @createDate 2023-08-26 18:55:20
*/
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories>
    implements CategoriesService{

}




