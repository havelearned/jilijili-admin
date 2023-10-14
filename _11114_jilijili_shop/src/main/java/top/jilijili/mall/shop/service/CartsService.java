package top.jilijili.mall.shop.service;

import top.jilijili.module.entity.Carts;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.vo.CartsVo;

import java.io.Serializable;
import java.util.List;

/**
* @author admin
* @description 针对表【shop_carts(购物车表)】的数据库操作Service
* @createDate 2023-08-26 18:55:20
*/
public interface CartsService extends IService<Carts> {

    /**
     * 通过用户id查询购物车信息
     *
     * @param userId
     * @return 返回用户的购物车数据
     */
    List<CartsVo> queryCartsByUserIdList(Serializable userId);
}
