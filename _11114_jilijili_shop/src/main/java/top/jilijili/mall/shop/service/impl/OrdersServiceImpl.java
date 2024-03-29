package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.jilijili.mall.shop.mapper.OrdersMapper;
import top.jilijili.mall.shop.service.OrdersService;
import top.jilijili.module.pojo.dto.shop.OrdersDto;
import top.jilijili.module.pojo.entity.shop.Orders;
import top.jilijili.module.pojo.vo.shop.OrderEChartsVo;
import top.jilijili.module.pojo.vo.shop.OrdersVo;

import java.io.Serializable;
import java.util.*;

/**
 * @author admin
 * @description 针对表【shop_orders(订单表)】的数据库操作Service实现
 * @createDate 2023-08-26 18:55:20
 */
@Service
@AllArgsConstructor
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
        implements OrdersService {

    private OrdersMapper ordersMapper;

    /**
     * 查询所有订单数据
     *
     * @param ordersDto 查询对象
     * @return
     */
    @Cacheable("ordersList")
    @Override
    public IPage<OrdersVo> getOrderList(OrdersDto ordersDto) {
        QueryWrapper<Orders> qw = new QueryWrapper<>();
        qw
                .eq(ordersDto.getOrderId() != null, "o.order_id", ordersDto.getOrderId())
                .eq(ordersDto.getUserId() != null, "o.user_id", ordersDto.getUserId())
                .eq(ordersDto.getOrderStatus() != null, "o.order_status", ordersDto.getOrderStatus())

                .between(ordersDto.getMax() != null && ordersDto.getMin() != null,
                        "o.total_amount", ordersDto.getMin(), ordersDto.getMax()
                )
                .between(ordersDto.getCreatedTime() != null && ordersDto.getComparisonTime() != null,
                        "o.created_time", ordersDto.getCreatedTime(), ordersDto.getComparisonTime()
                ).orderByDesc("o.created_time");
        IPage<OrdersVo> page = new Page<>(ordersDto.getPage(), ordersDto.getSize());

        return ordersMapper.selectOrderList(page, qw);

    }

    /**
     * 通过订单id查询订单详情
     *
     * @param orderId
     * @return
     */
    @Override
    public OrdersVo getOrderInfoById(Serializable orderId) {
        return this.ordersMapper.selectOrderInfoById(orderId);


    }

    /**
     * 通过用户id查询用户订单
     *
     * @param ordersDto
     * @return
     */
    @Override
    public IPage<OrdersVo> getOrderListByUserId(OrdersDto ordersDto) {
        IPage<OrdersVo> page = new Page(ordersDto.getPage(), ordersDto.getSize());
        QueryWrapper<Orders> qw = new QueryWrapper<>();
        qw
                .eq(Objects.nonNull(ordersDto.getOrderId()), "o.order_id", ordersDto.getOrderId())
                .eq("o.user_id", ordersDto.getUserId())
                .eq(ordersDto.getOrderStatus() != null, "o.order_status", ordersDto.getOrderStatus())
                .between(Objects.nonNull(ordersDto.getOrderDateFormat()), "o.order_date", ordersDto.getOrderDateFormat(), new Date())
                .orderByDesc(true, "o.created_time", "o.order_date");
        return this.ordersMapper.selectOrderListByUserId(page, qw);

    }

    /**
     * 查询订单今日数据
     *
     * @param ordersDto
     * @return
     */
    @Override
    public Map<String, Object> queryOrdersTodayData(OrdersDto ordersDto) {
        OrderEChartsVo orderEChartsVo = ordersMapper.queryOrderToDayData();
        if (ordersDto.getCreatedTime() == null) {
            Calendar instance = Calendar.getInstance();
            instance.add(Calendar.DAY_OF_YEAR, -7);
            ordersDto.setCreatedTime(new Date());
            ordersDto.setComparisonTime(instance.getTime());
        }
        List<OrderEChartsVo> voList = this.ordersMapper.queryOrdersByDate(ordersDto);
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("toDayData", orderEChartsVo);
        resultMap.put("eCharts", voList);

        return resultMap;
    }
}




