package top.jilijili.mall.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.dto.shop.ProductsDto;
import top.jilijili.module.pojo.entity.shop.Products;
import top.jilijili.module.pojo.vo.shop.ProductsVo;

import java.util.List;
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

    /**
     * 推荐商品内容
     * @param productsDto 查询实体
     * @return 推荐商品列表
     */
    Result<IPage<ProductsVo>> recommendedProductSearch(ProductsDto productsDto);


    /**
     * 设置所有库存的商品进行统一预热处理
     *
     * @param idList 库存商品的ID列表
     * @return 预热处理结果
     */
    Result<Boolean> stockUnifiedPreheatingProcess(List<Long> idList);
}
