package top.jilijili.shop.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.CartItems;
import top.jilijili.module.entity.Orders;
import top.jilijili.module.entity.Products;
import top.jilijili.module.entity.dto.OrdersDto;
import top.jilijili.module.entity.dto.ProductsDto;
import top.jilijili.module.entity.vo.CartsVo;
import top.jilijili.module.entity.vo.OrdersVo;
import top.jilijili.module.entity.vo.ProductsVo;

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
}
