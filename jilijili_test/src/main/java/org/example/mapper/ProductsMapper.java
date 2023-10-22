package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.pojo.dto.shop.ProductsDto;
import top.jilijili.module.pojo.entity.shop.Products;
import top.jilijili.module.pojo.vo.shop.ProductsEChartsVo;

import java.util.List;

/**
 * @author admin
 * @description 针对表【shop_products(商品表)】的数据库操作Mapper
 * @createDate 2023-08-26 18:55:20
 * @Entity top.jilijili.module.entity.Products
 */
@Mapper
public interface ProductsMapper extends BaseMapper<Products> {



}




