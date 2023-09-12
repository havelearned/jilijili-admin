package top.jilijili.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.jilijili.shop.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.shop.entity.dto.ProductsDto;
import top.jilijili.shop.entity.vo.ProductsVo;

/**
* @author admin
* @description 针对表【shop_products(商品表)】的数据库操作Service
* @createDate 2023-08-26 18:55:20
*/
public interface ProductsService extends IService<Products> {

    /**
     * 分页查询商品
     * @param productsDto
     * @return
     */
    IPage<ProductsVo> queryProductList(ProductsDto productsDto);
}
