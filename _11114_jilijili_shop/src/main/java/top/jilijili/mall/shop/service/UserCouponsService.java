package top.jilijili.mall.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.entity.shop.UserCoupons;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.pojo.dto.shop.UserCouponsDto;
import top.jilijili.module.pojo.vo.shop.UserCouponsVo;

import java.io.Serializable;

/**
* @author admin
* @description 针对表【shop_user_coupons】的数据库操作Service
* @createDate 2023-10-06 23:14:17
*/
public interface UserCouponsService extends IService<UserCoupons> {

    Result<IPage<UserCouponsVo>> selectAllUC(UserCouponsDto userCouponsDto);

    Result<UserCouponsVo> selectOneUC(Serializable id);

    Result<UserCouponsVo> insertUc(UserCouponsDto userCouponsDto);

    Result<UserCouponsVo> updateUC(UserCouponsDto userCouponsDto);
}
