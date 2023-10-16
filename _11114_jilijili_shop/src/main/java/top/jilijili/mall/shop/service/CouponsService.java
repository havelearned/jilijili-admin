package top.jilijili.mall.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.common.entity.Result;
import top.jilijili.module.entity.Coupons;
import top.jilijili.module.entity.dto.CouponsDto;
import top.jilijili.module.entity.vo.CouponsVo;
import top.jilijili.module.entity.vo.UserWithCouponsVo;

import java.io.Serializable;

/**
* @author admin
* @description 针对表【shop_coupons】的数据库操作Service
* @createDate 2023-10-06 23:14:17
*/
public interface CouponsService extends IService<Coupons> {

    Result<IPage<UserWithCouponsVo>> selectAllCoupons(CouponsDto coupons);

    Result<CouponsVo> selectOneCoupons(Serializable id);

    Result<CouponsVo> insertCoupons(CouponsDto couponsDto);

    Result<CouponsVo> updateCoupons(CouponsDto couponsDto);
}
