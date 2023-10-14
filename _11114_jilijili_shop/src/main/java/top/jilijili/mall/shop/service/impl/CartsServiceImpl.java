package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.jilijili.mall.shop.mapper.CartsMapper;
import top.jilijili.mall.shop.mapper.ConvertMapper;
import top.jilijili.module.entity.Carts;
import top.jilijili.module.entity.vo.CartsVo;
import top.jilijili.mall.shop.service.CartItemsService;
import top.jilijili.mall.shop.service.CartsService;
import top.jilijili.mall.shop.service.ProductsService;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @description 针对表【shop_carts(购物车表)】的数据库操作Service实现
 * @createDate 2023-08-26 18:55:20
 */
@Service
@AllArgsConstructor
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts>
        implements CartsService {


    CartItemsService cartItemsService;
    ProductsService productsService;
    CartsMapper cartsMapper;
    ConvertMapper convertMapper;


    public List<CartsVo> queryCartsByUserIdList(Serializable userId) {
        return this.cartsMapper.queryByUserId(userId);
    }
}




