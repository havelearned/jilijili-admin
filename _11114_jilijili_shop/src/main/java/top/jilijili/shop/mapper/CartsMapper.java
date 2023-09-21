package top.jilijili.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.entity.Carts;
import top.jilijili.module.entity.vo.CartsVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @description 针对表【shop_carts(购物车表)】的数据库操作Mapper
 * @createDate 2023-08-26 18:55:20
 * @Entity top.jilijili.shop.entity.Carts
 */
@Mapper
public interface CartsMapper extends BaseMapper<Carts> {


    /**
     * 通过用户id查询用户的购物车列表
     *
     * @param userId
     * @return 购物车列表
     */
    List<CartsVo> queryByUserId(@Param("userId") Serializable userId);

}




