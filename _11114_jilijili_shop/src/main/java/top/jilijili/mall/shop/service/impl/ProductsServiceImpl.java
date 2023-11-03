package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.shop.mapper.ConvertMapper;
import top.jilijili.mall.shop.mapper.ProductsMapper;
import top.jilijili.mall.shop.service.ProductsService;
import top.jilijili.mall.shop.service.RecommendService;
import top.jilijili.module.pojo.dto.shop.ProductsDto;
import top.jilijili.module.pojo.entity.shop.Products;
import top.jilijili.module.pojo.vo.shop.ProductsEChartsVo;
import top.jilijili.module.pojo.vo.shop.ProductsVo;

import java.util.*;

import static top.jilijili.common.utils.KeyConstants.PRODUCT_HEA_KEY;

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
    private RecommendService recommendService;
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 通过Item推荐商品搜搜内容
     *
     * @param productsDto 查询实体
     * @return 推荐商品列表
     */
    @Override
    public Result<IPage<ProductsVo>> recommendedProductSearch(ProductsDto productsDto) {

        // 没有匹配到商品id,正常查询,忽略了user方式推荐
        Long count = this.lambdaQuery()
                .eq(Products::getProductId, productsDto.getProductId()).count();
        if (count < 1) {
            return Result.ok(this.queryProductList(productsDto));
        }

        //使用不同的种子值,保证推荐数据尽量变化性
        Random random = new Random(System.currentTimeMillis());
        int randomValue = random.nextInt(50 - productsDto.getSize() + 1) + productsDto.getSize();
        List<Long> recommend = recommendService.recommend(Long.valueOf(productsDto.getProductId()), randomValue);
        IPage<ProductsVo> convert = getRecommendedPage(productsDto, recommend);
        return Result.ok(convert);
    }


    /**
     * 做推荐数据
     *
     * @param productsDto 查询dto
     * @param recommend   推荐id
     * @return 商品分页数据
     */
    private IPage<ProductsVo> getRecommendedPage(ProductsDto productsDto, List<Long> recommend) {
        List<Products> products = this.listByIds(recommend);

        Page<Products> page = new Page<>(productsDto.getPage(), productsDto.getSize());

        // 如果推荐数据小于返回要求数量,随机查询填充
        if (productsDto.getSize() > products.size()) {
            List<Products> randmProductsList = this.productsMapper.queryProductRandomByNum(productsDto.getSize() - products.size());
            products.addAll(randmProductsList);
        }
        // 统一分页返回数据
        page.setRecords(products);
        page.setCurrent(productsDto.getPage());
        page.setSize(products.size());
        page.setTotal(this.lambdaQuery().count());
        return page.convert(this.convertMapper::toProductsVo);

    }


    /**
     * 对库存进行统一预热处理
     *
     * @param idList 产品ID列表
     * @return 设置结果及数量
     */
    @Override
    public Result<Boolean> stockUnifiedPreheatingProcess(List<Long> idList) {
        List<Products> products = this.lambdaQuery().in(Products::getProductId, idList).list();
        int successCount = 0;
        int failCount =0;

        for (Products product : products) {
            if (product.getStockQuantity() >= 0) {
                this.redisTemplate.opsForValue().set(PRODUCT_HEA_KEY+product.getProductId(), product.getStockQuantity());
                successCount++;
            }else{
                failCount++;
            }
        }
        return Result.ok(true,
                """
                成功:%d个
                失败:%d个
                """.formatted(successCount,failCount));
    }

    /**
     * 查询商品列表
     *
     * @param productsDto
     * @return
     */
    @Override
    public IPage<ProductsVo> queryProductList(ProductsDto productsDto) {
        LambdaQueryWrapper<Products> qw = new LambdaQueryWrapper<>();
        qw.eq(productsDto.getCategoryId() != null, Products::getCategoryId, productsDto.getCategoryId())
                .like(StringUtils.hasText(productsDto.getProductName()), Products::getProductName, productsDto.getProductName())
                .like(StringUtils.hasText(productsDto.getDescription()), Products::getDescription, productsDto.getDescription())
                .between(Objects.nonNull(productsDto.getMax()) && Objects.nonNull(productsDto.getMin()),
                        Products::getPrice, productsDto.getMin(), productsDto.getMax())
                .between(Objects.nonNull(productsDto.getCreatedTime()),
                        Products::getCreatedTime, productsDto.getCreatedTime(), productsDto.getComparisonTime())
                .orderByDesc(Products::getCreatedTime);

        // BUG 未分页
        Page<Products> iPage = this.productsMapper.selectPage(new Page<Products>(productsDto.getPage(), productsDto.getSize()), qw);
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




