package top.jilijili.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.entity.Orders;
import top.jilijili.module.entity.dto.OrdersDto;
import top.jilijili.module.entity.vo.OrderEChartsVo;

import java.util.List;

/**
 * @author admin
 * @description 针对表【shop_orders(订单表)】的数据库操作Mapper
 * @createDate 2023-08-26 18:55:20
 * @Entity top.jilijili.shop.entity.Orders
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {


    /**
     * 获取指定时间内的总订单量
     * @param ordersDto 查询条件
     * @return
     */
    List<OrderEChartsVo> queryOrdersByDate(@Param("orderDto") OrdersDto ordersDto);


    /**
     * 查询今日订单数量
     *
     * @return
     */
    OrderEChartsVo queryOrderToDayData();

}




