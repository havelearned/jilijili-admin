package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.mall.shop.mapper.CartItemsMapper;
import top.jilijili.module.pojo.entity.shop.CartItems;
import top.jilijili.mall.shop.service.CartItemsService;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【shop_cart_items(购物车明细表)】的数据库操作Service实现
* @createDate 2023-08-26 18:55:20
*/
@Service
public class CartItemsServiceImpl extends ServiceImpl<CartItemsMapper, CartItems>
    implements CartItemsService{

}




