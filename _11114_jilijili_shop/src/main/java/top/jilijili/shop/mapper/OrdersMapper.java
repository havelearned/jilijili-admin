package top.jilijili.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.shop.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【shop_orders(订单表)】的数据库操作Mapper
* @createDate 2023-08-26 18:55:20
* @Entity top.jilijili.shop.entity.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




