package top.jilijili.mall.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.pojo.entity.shop.UserCoupons;
import top.jilijili.module.pojo.vo.shop.UserWithCouponsVo;

import java.util.Map;

/**
* @author admin
* @description 针对表【shop_user_coupons】的数据库操作Mapper
* @createDate 2023-10-06 23:14:17
* @Entity top.jilijili.module.entity.UserCoupons
*/
@Mapper
public interface UserCouponsMapper extends BaseMapper<UserCoupons> {

//    IPage<UserCouponsVo> selectByUserId(@Param("page") Page<UserWithCouponsVo> page,
//                                        @Param("userId")Serializable userId);


    @MapKey("user_coupon_id")
    IPage<Map<String, Object>> selectByUserId(@Param("page") Page<UserWithCouponsVo> page,
                                              @Param(Constants.WRAPPER) Wrapper wq);
}




