package top.jilijili.mall.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import top.jilijili.common.entity.Item;
import top.jilijili.common.entity.Result;
import top.jilijili.common.group.Insert;
import top.jilijili.mall.shop.mapper.ConvertMapper;
import top.jilijili.mall.shop.service.CartsService;
import top.jilijili.mall.shop.service.CategoriesService;
import top.jilijili.mall.shop.service.ProductsService;
import top.jilijili.module.pojo.dto.shop.CategoriesDto;
import top.jilijili.module.pojo.dto.shop.ProductsDto;
import top.jilijili.module.pojo.entity.shop.Categories;
import top.jilijili.module.pojo.entity.shop.Products;
import top.jilijili.module.pojo.vo.shop.CartsVo;
import top.jilijili.module.pojo.vo.shop.ProductsVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 商品管理
 *
 * @author makejava
 * @since 2023-08-26 18:57:53
 */
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController extends ShopSuperController {
    /**
     * 服务对象
     */
    private ProductsService productsService;
    private ConvertMapper convertMapper;
    private CartsService cartsService;
    private CategoriesService categoriesService;

    /*-------------------------------------分类信息--------------------------------------------*/


    /**
     * 通过一个或者多个id删除商品分类
     *
     * @param idList 要给或者多个id
     * @return
     */
    @DeleteMapping("/categories")
    public Mono<Result<Object>> categoriesDelete(@RequestBody List<Serializable> idList) {
        return Mono.fromCallable(() -> {
            Long count = this.productsService.lambdaQuery().in(Products::getCategoryId, idList).count();
            if (count >= 1) {
                return Result.ok(null, "该分类下有商品无法删除");
            }
            boolean deleted = this.categoriesService.removeBatchByIds(idList);
            if (deleted) {
                return Result.ok(null, "操作成功");
            }
            return Result.fail("操作失败");
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 添加或者修改商品分类
     *
     * @param dto 请求参数
     * @return
     */
    @PostMapping("/categories")
    public Mono<Result<Categories>> add(@RequestBody @Validated(value = Insert.class) CategoriesDto dto) {
        return Mono.just(this.categoriesService.addOrUpdate(dto));
    }

    @PutMapping("/categories")
    public Mono<Result<Categories>> edit(@RequestBody CategoriesDto dto) {
        return Mono.just(this.categoriesService.addOrUpdate(dto));
    }

    /**
     * 分页查询商品分类字典列表
     *
     * @param dto
     * @return label, value object a list
     */
    @GetMapping("/categories/list/dict")
    public Mono<Result<IPage<Item>>> getCategoriesListDict(CategoriesDto dto) {
        return Mono.just(this.categoriesService.getCategoriesListDict(dto));
    }

    /**
     * 分页查询商品分类列表
     *
     * @param categoryDto
     * @return 分类列表
     */
    @GetMapping("/categories/list")
    public Mono<Result<Page<Categories>>> getCategoriesList(CategoriesDto categoryDto) {
        return Mono.just(this.categoriesService.getCategoriesList(categoryDto));
    }

    /**
     * 通过Id获取商品分类
     *
     * @param id
     * @return 分类Entity
     */
    @GetMapping("/categories/{id}")
    public Mono<Result<Categories>> getCategories(@PathVariable Long id) {
        return Mono.just(Result.ok(this.categoriesService.getById(id)));
    }


    /**
     * 商品今日上架总数量
     * 商品指定时间段的上架统计图数据
     * 商品总数量
     *
     * @param productsDto 查询参数
     * @return Mono<Result < Map < String, Object>>> 商品可视化数据
     */
    @GetMapping("/productsToDayInfo")
    public Mono<Result<Map<String, Object>>> productsToDayInfo(@RequestBody ProductsDto productsDto) {
        return Mono.just(Result.ok(productsService.queryProductsTodayInfo(productsDto)));
    }



    /*-------------------------------------商品--------------------------------------------*/

    /**
     * 推荐商品物品
     *
     * @param productsDto 查询实体
     * @return 推荐商品列表
     */
    @GetMapping("/search")
    public Mono<Result<IPage<ProductsVo>>> recommendedProductSearchByItem(ProductsDto productsDto) {
        return Mono.just(this.productsService.recommendedProductSearch(productsDto));
    }


    /**
     * 获取商品列表
     *
     * @param productsDto
     * @return
     */
    @GetMapping("/list")
    public Mono<Result<IPage<ProductsVo>>> selectList(ProductsDto productsDto) {
        return Mono.just(Result.ok(this.productsService.queryProductList(productsDto)));
    }

    /**
     * 通过id获取商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<Result<Object>> selectOne(@PathVariable Serializable id) {
        Products products = this.productsService.getById(id);
        return Mono.just(Result.ok(this.convertMapper.toProductsVo(products)));
    }

    /**
     * 添加商品信息
     *
     * @param productsDto 添加商品
     * @return
     */
    @PostMapping
    public Mono<Result<Object>> add(@RequestBody ProductsDto productsDto) {
        Products products = this.convertMapper.toProducts(productsDto);
        boolean isSuccess = this.productsService.save(products);
        if (isSuccess) {
            return Mono.just(Result.ok("操作成功"));
        }
        return Mono.just(Result.fail("操作失败"));
    }

    /**
     * 修改商品信息
     *
     * @param productsDto 修改内容
     * @return
     */
    @PutMapping
    public Mono<Result<String>> updateProduct(@RequestBody ProductsDto productsDto) {
        Products products = this.convertMapper.toProducts(productsDto);
        boolean isSuccess = this.productsService.updateById(products);
        if (isSuccess) {
            return Mono.just(Result.ok("操作成功"));
        }
        return Mono.just(Result.fail("操作失败"));
    }


    /**
     * 通过id删除商品信息
     *
     * @param idList 一个或者多个id
     * @return
     */
    @DeleteMapping
    public Mono<Result<Object>> delete(@RequestBody List<Long> idList) {
        return Mono.fromCallable(() -> this.productsService.removeBatchByIds(idList))
                .map(success -> Boolean.TRUE.equals(success) ? Result.ok(null, "操作成功") : Result.fail("操作失败"))
                .subscribeOn(Schedulers.boundedElastic());
    }



    /*-------------------------------------购物车--------------------------------------------*/


    /**
     * 通过用户id查询购物车信息
     *
     * @param userId
     * @return 返回用户的购物车数据
     */
    @GetMapping("/cart/list/{userId}")
    public Mono<Result<List<CartsVo>>> cartList(@PathVariable("userId") Serializable userId) {
        List<CartsVo> list = this.cartsService.queryCartsByUserIdList(userId);
        return Mono.just(Result.ok(list));
    }

    /**
     * 删除购物车一个或者多个
     *
     * @param idList
     * @return
     */
    @DeleteMapping("/cart")
    public Mono<Result<?>> delCart(List<Serializable> idList) {
        return Mono.fromCallable(() -> {
            boolean b = cartsService.removeBatchByIds(idList);
            if (b) {
                return Result.ok("操作成功");
            } else {
                return Result.fail("操作失败");
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}

