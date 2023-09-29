package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.module.entity.Products;
import top.jilijili.module.entity.dto.ProductsDto;
import top.jilijili.module.entity.vo.ProductsEChartsVo;
import top.jilijili.module.entity.vo.ProductsVo;
import top.jilijili.shop.mapper.ConvertMapper;
import top.jilijili.shop.mapper.ProductsMapper;
import top.jilijili.shop.service.ProductsService;

import java.util.*;

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

    // todo 通过上传图片相似度查询商品,无限延期


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

    /**
     * # 商品今日上架总数量
     * # 商品指定时间段的上架统计图数据
     * # 商品总数量
     *
     * @param productsDto 查询参数
     * @return Mono<Result < Map < String, Object>>> 商品可视化数据
     */
    @Override
    public Map<String, Object> queryProductsTodayInfo(ProductsDto productsDto) {
        // 查询今日上架商品数量
        Integer todayData = this.productsMapper.queryProductsByToDayData();

        // 查询总上架商品数量
        Long count = this.lambdaQuery().count();

        // 默认查询7天商品数据
        List<ProductsEChartsVo> productsEChartsVos;
        if (productsDto.getCreatedTime() == null) {
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.DAY_OF_YEAR, -7);
            productsDto.setCreatedTime(instance.getTime());
            productsDto.setComparisonTime(new Date());
        }
        productsEChartsVos = this.productsMapper.queryProductsByDateData(productsDto);

        HashMap<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("todayData", todayData);
        resultMap.put("eCharts", productsEChartsVos);
        resultMap.put("count", count);
        return resultMap;
    }
}




