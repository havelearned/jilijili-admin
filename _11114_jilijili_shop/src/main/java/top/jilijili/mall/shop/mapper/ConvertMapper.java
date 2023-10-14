package top.jilijili.mall.shop.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.*;
import top.jilijili.module.entity.dto.*;
import top.jilijili.module.entity.vo.*;

/**
 * @author Amani
 * @date 2023年08月26日 20:49
 */
@Mapper(componentModel = "spring")
@Component
public interface ConvertMapper {
    Products toProducts(ProductsDto productsDto);

    ProductsVo toProductsVo(Products products);

    Orders toOrders(OrdersDto ordersDto);

    OrdersVo toOrdersVo(Orders orders);

    CartsVo toCartsVo(CartItems cartItems);


    /*==============================卡密==============================*/
    CouponsVo toCouponsVo(Coupons coupons);

    Coupons toCoupons(CouponsDto couponsDto);

    PromocodesVo toPromoCodesVo(Promocodes promocodes);

    Promocodes toPromoCodes(PromocodesDto promocodesDto);

    UserCouponsVo toUserCouponsVo(UserCoupons userCoupons);

    UserCoupons toUserCoupons(UserCouponsDto userCouponsDto);


}
