package top.jilijili.shop.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.shop.entity.CartItems;
import top.jilijili.shop.entity.Orders;
import top.jilijili.shop.entity.Products;
import top.jilijili.shop.entity.dto.OrdersDto;
import top.jilijili.shop.entity.dto.ProductsDto;
import top.jilijili.shop.entity.vo.CartsVo;
import top.jilijili.shop.entity.vo.OrdersVo;
import top.jilijili.shop.entity.vo.ProductsVo;

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
