package top.jilijili.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.entity.Products;
import top.jilijili.module.entity.dto.ProductsDto;
import top.jilijili.module.entity.vo.ProductsEChartsVo;

import java.util.List;

/**
 * @author admin
 * @description 针对表【shop_products(商品表)】的数据库操作Mapper
 * @createDate 2023-08-26 18:55:20
 * @Entity top.jilijili.shop.entity.Products
 */
@Mapper
public interface ProductsMapper extends BaseMapper<Products> {

    /**
     * 获取商品指定时间段的上架统计图数据
     */
    List<ProductsEChartsVo> queryProductsByDateData(@Param("productsDto")
                                                    ProductsDto productsDto);

    /**
     * 获取商品今日上架总数量
     *
     * @return
     */
    Integer queryProductsByToDayData();

}




