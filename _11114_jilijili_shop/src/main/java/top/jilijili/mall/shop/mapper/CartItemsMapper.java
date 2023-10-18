package top.jilijili.mall.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.pojo.entity.shop.CartItems;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【shop_cart_items(购物车明细表)】的数据库操作Mapper
* @createDate 2023-08-26 18:55:20
* @Entity top.jilijili.module.entity.CartItems
*/
@Mapper
public interface CartItemsMapper extends BaseMapper<CartItems> {

}




