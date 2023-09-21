package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.module.entity.OrderItems;
import top.jilijili.shop.service.OrderItemsService;
import top.jilijili.shop.mapper.OrderItemsMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【shop_order_items(订单明细表)】的数据库操作Service实现
* @createDate 2023-08-26 18:55:20
*/
@Service
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemsMapper, OrderItems>
    implements OrderItemsService{

}




