package top.jilijili.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import top.jilijili.common.entity.Result;
import top.jilijili.module.entity.Products;
import top.jilijili.module.entity.dto.ProductsDto;
import top.jilijili.module.entity.vo.CartsVo;
import top.jilijili.module.entity.vo.ProductsVo;
import top.jilijili.shop.mapper.ConvertMapper;
import top.jilijili.shop.service.CartsService;
import top.jilijili.shop.service.ProductsService;

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
    private ProductsService shopProductsService;
    private ConvertMapper convertMapper;
    private CartsService cartsService;

    /**
     * # 商品今日上架总数量
     * # 商品指定时间段的上架统计图数据
     * # 商品总数量
     *
     * @param productsDto 查询参数
     * @return Mono<Result < Map < String, Object>>> 商品可视化数据
     */
    @GetMapping("/productsToDayInfo")
    public Mono<Result<Map<String, Object>>> productsToDayInfo(@RequestBody ProductsDto productsDto) {
        return Mono.just(Result.ok(shopProductsService.queryProductsTodayInfo(productsDto)));
    }



    /*-------------------------------------商品--------------------------------------------*/

    /**
     * 获取商品列表
     *
     * @param productsDto
     * @return
     */
    @GetMapping("/list")
    public Mono<Result<IPage<ProductsVo>>> selectList(ProductsDto productsDto) {
        return Mono.just(Result.ok(this.shopProductsService.queryProductList(productsDto)));
    }

    /**
     * 通过id获取商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<Result<Object>> selectOne(@PathVariable Serializable id) {
        Products products = this.shopProductsService.getById(id);
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
        boolean isSuccess = this.shopProductsService.save(products);
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
        boolean isSuccess = this.shopProductsService.updateById(products);
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
    public Mono<Result<Object>> delete(List<Serializable> idList) {
        return Mono.fromCallable(() -> this.shopProductsService.removeBatchByIds(idList))
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

