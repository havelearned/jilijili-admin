package top.jilijili.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.Products;
import top.jilijili.module.entity.dto.ProductsDto;
import top.jilijili.module.entity.vo.ProductsVo;

import java.util.Map;

/**
 * @author admin
 * @description 针对表【shop_products(商品表)】的数据库操作Service
 * @createDate 2023-08-26 18:55:20
 */
public interface ProductsService extends IService<Products> {

    /**
     * 分页查询商品
     *
     * @param productsDto
     * @return
     */
    IPage<ProductsVo> queryProductList(ProductsDto productsDto);


    /**
     * 商品今日上架总数量
     * 商品指定时间段的上架统计图数据
     * 商品总数量
     *
     * @param productsDto 查询参数
     * @return Mono<Result < Map < String, Object>>> 商品可视化数据
     */
    Map<String, Object> queryProductsTodayInfo(ProductsDto productsDto);
}
