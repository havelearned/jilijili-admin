package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.mall.shop.mapper.ConvertMapper;
import top.jilijili.mall.shop.mapper.ProductsMapper;
import top.jilijili.mall.shop.service.ProductsService;
import top.jilijili.module.pojo.dto.shop.ProductsDto;
import top.jilijili.module.pojo.entity.shop.Products;
import top.jilijili.module.pojo.vo.shop.ProductsEChartsVo;
import top.jilijili.module.pojo.vo.shop.ProductsVo;

import java.util.*;

/**
 * @author admin
 * @description 针对表【shop_products(商品表)】的数据库操作Service实现
 * @createDate 2023-08-26 18:55:20
 */
@Slf4j
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
        iPage = this.lambdaQuery()
                .eq(productsDto.getCategoryId() != null, Products::getCategoryId, productsDto.getCategoryId())
                .like(StringUtils.hasText(productsDto.getProductName()), Products::getProductName, productsDto.getProductName())
                .like(StringUtils.hasText(productsDto.getDescription()), Products::getDescription, productsDto.getDescription())
                .between(Objects.nonNull(productsDto.getMax()) && Objects.nonNull(productsDto.getMin()),
                        Products::getPrice, productsDto.getMin(), productsDto.getMax())
                .between(Objects.nonNull(productsDto.getCreatedTime()),
                        Products::getCreatedTime, productsDto.getCreatedTime(), productsDto.getComparisonTime())
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




