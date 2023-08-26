package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.shop.entity.Orders;
import top.jilijili.shop.service.OrdersService;
import top.jilijili.shop.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【shop_orders(订单表)】的数据库操作Service实现
* @createDate 2023-08-26 18:55:20
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}




