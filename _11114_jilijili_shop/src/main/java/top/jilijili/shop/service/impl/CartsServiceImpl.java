package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.shop.entity.Carts;
import top.jilijili.shop.service.CartsService;
import top.jilijili.shop.mapper.CartsMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【shop_carts(购物车表)】的数据库操作Service实现
* @createDate 2023-08-26 18:55:20
*/
@Service
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts>
    implements CartsService{

}




