package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.jilijili.module.entity.Orders;
import top.jilijili.module.entity.dto.OrdersDto;
import top.jilijili.module.entity.vo.OrderEChartsVo;
import top.jilijili.shop.mapper.OrdersMapper;
import top.jilijili.shop.service.OrdersService;

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




