package top.jilijili.mall.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.Categories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【shop_categories(商品分类表)】的数据库操作Mapper
* @createDate 2023-08-26 18:55:20
* @Entity top.jilijili.module.entity.Categories
*/
@Mapper
public interface CategoriesMapper extends BaseMapper<Categories> {

}




