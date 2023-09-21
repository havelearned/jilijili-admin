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

    Map<String, Object> queryProductsTodayInfo(ProductsDto productsDto);
}
