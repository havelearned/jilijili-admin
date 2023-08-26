package top.jilijili.shop.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jilijili.common.control.SuperController;
import top.jilijili.shop.service.ProductsService;

/**
 * 商品表(ShopProducts)表控制层
 *
 * @author makejava
 * @since 2023-08-26 18:57:53
 */
@RestController
@RequestMapping("/shopProducts")
@AllArgsConstructor
public class ShopProductsController extends SuperController {
    /**
     * 服务对象
     */
    private ProductsService shopProductsService;

}

