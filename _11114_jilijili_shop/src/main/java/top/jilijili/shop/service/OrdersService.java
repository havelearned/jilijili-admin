package top.jilijili.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.Orders;
import top.jilijili.module.entity.dto.OrdersDto;
import top.jilijili.module.entity.vo.OrdersVo;

import java.io.Serializable;
import java.util.Map;

/**
 * @author admin
 * @description 针对表【shop_orders(订单表)】的数据库操作Service
 * @createDate 2023-08-26 18:55:20
 */
public interface OrdersService extends IService<Orders> {

    /**
     * # 总订单量:完成,未完成,过期
     * # 获取指定时间内的总订单量:完成,未完成,过期
     * # 订单今日数量:完成 未完成 过期
     *
     * @param ordersDto
     * @return
     */
    Map<String, Object> queryOrdersTodayData(OrdersDto ordersDto);

    /**
     * 通过用户id查询用户订单
     *
     * @param ordersDto
     * @return
     */
    IPage<OrdersVo> getOrderListByUserId(OrdersDto ordersDto);

    /**
     * 查询所有订单数据
     *
     * @param ordersDto 查询对象
     * @return
     */
    IPage<OrdersVo> getOrderList(OrdersDto ordersDto);

    /**
     * 通过订单id查询订单详情
     *
     * @param orderId
     * @return
     */
    OrdersVo getOrderInfoById(Serializable orderId);
}
