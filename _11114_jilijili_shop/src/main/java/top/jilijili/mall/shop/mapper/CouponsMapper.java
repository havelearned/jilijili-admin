package top.jilijili.mall.shop.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.pojo.entity.shop.Coupons;
import top.jilijili.module.pojo.vo.shop.CouponsVo;
import top.jilijili.module.pojo.vo.shop.UserWithCouponsVo;

/**
 * @author admin
 * @description 针对表【shop_coupons】的数据库操作Mapper
 * @createDate 2023-10-06 23:14:17
 * @Entity top.jilijili.module.entity.Coupons
 */
@Mapper
public interface CouponsMapper extends BaseMapper<Coupons> {

    IPage<UserWithCouponsVo> selectAllCoupons(IPage<UserWithCouponsVo> page, @Param(Constants.WRAPPER) QueryWrapper<CouponsVo> queryWrapper);
}




