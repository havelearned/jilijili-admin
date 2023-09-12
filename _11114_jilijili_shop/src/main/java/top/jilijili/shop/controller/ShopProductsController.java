package top.jilijili.shop.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import top.jilijili.common.entity.Result;
import top.jilijili.shop.entity.Products;
import top.jilijili.shop.entity.dto.ProductsDto;
import top.jilijili.shop.entity.vo.CartsVo;
import top.jilijili.shop.entity.vo.ProductsVo;
import top.jilijili.shop.mapper.ConvertMapper;
import top.jilijili.shop.service.CartsService;
import top.jilijili.shop.service.ProductsService;

import java.io.Serializable;
import java.util.List;

/**
 * 商品管理
 *
 * @author makejava
 * @since 2023-08-26 18:57:53
 */
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ShopProductsController extends ShopSuperController {
    /**
     * 服务对象
     */
    private ProductsService shopProductsService;
    private ConvertMapper convertMapper;
    private CartsService cartsService;



    /*-------------------------------------商品--------------------------------------------*/

    /**
     * 获取商品列表
     *
     * @param productsDto
     * @return
     */
    @GetMapping("/list")
    public Mono<Result<?>> selectList(ProductsDto productsDto) {
        IPage<ProductsVo> voIPage = this.shopProductsService.queryProductList(productsDto);
        return Mono.just(Result.ok(voIPage));
    }

    /**
     * 通过id获取商品信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<Result<?>> selectOne(@PathVariable Serializable id) {
        Products byId = this.shopProductsService.getById(id);
        return Mono.just(Result.ok(this.convertMapper.toProductsVo(byId)));
    }

    /**
     * 添加商品信息
     *
     * @param productsDto 添加商品
     * @return
     */
    @PostMapping
    public Mono<Result<?>> add(@RequestBody ProductsDto productsDto) {
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
    public Mono<Result<?>> delete(@RequestBody List<Serializable> idList) {
        return Mono.fromCallable(() -> this.shopProductsService.removeBatchByIds(idList))
                .map(success -> success ? Result.ok(null, "操作成功") : Result.fail("操作失败"));
    }



    /*-------------------------------------购物车--------------------------------------------*/


    /**
     * 通过用户id查询购物车信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/cart/list/{userId}")
    public Mono<Result<?>> cartList(@PathVariable("userId") Serializable userId) {
        List<CartsVo> list = this.cartsService.queryCartsByUserIdList(userId);
        return Mono.just(Result.ok(list));
    }

    /**
     * 删除购物车
     */
    @DeleteMapping("/cart")
    public Mono<Result<?>> delCart(@RequestParam("idList") List<Serializable> idList) {
        cartsService.removeBatchByIds(idList);
        return Mono.just(Result.ok());
    }
}

