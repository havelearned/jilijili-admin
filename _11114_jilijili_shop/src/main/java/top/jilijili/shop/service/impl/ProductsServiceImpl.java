package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.shop.entity.Products;
import top.jilijili.shop.entity.dto.ProductsDto;
import top.jilijili.shop.entity.vo.ProductsVo;
import top.jilijili.shop.mapper.ConvertMapper;
import top.jilijili.shop.mapper.ProductsMapper;
import top.jilijili.shop.service.ProductsService;

/**
 * @author admin
 * @description 针对表【shop_products(商品表)】的数据库操作Service实现
 * @createDate 2023-08-26 18:55:20
 */
@Service
@AllArgsConstructor
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products>
        implements ProductsService {

    private ProductsMapper productsMapper;
    private ConvertMapper convertMapper;

    // todo 通过上传图片相似度查询商品


    /**
     * 查询商品列表
     *
     * @param productsDto
     * @return
     */
    @Override
    public IPage<ProductsVo> queryProductList(ProductsDto productsDto) {
        IPage<Products> iPage = new Page<>(productsDto.getPage(), productsDto.getSize());
        Products products = this.convertMapper.toProducts(productsDto);
        iPage = this.lambdaQuery()
                .like(StringUtils.hasText(products.getProductName()), Products::getProductName, products.getProductName())
                .like(StringUtils.hasText(products.getDescription()), Products::getDescription, products.getDescription())
                .orderByDesc(Products::getCreatedTime)
                .page(iPage);
        return iPage.convert(this.convertMapper::toProductsVo);
    }
}




