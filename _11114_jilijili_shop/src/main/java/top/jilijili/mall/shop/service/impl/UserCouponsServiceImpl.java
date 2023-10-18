package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.entity.shop.UserCoupons;
import top.jilijili.module.pojo.dto.shop.UserCouponsDto;
import top.jilijili.module.pojo.vo.shop.UserCouponsVo;
import top.jilijili.mall.shop.service.UserCouponsService;
import top.jilijili.mall.shop.mapper.UserCouponsMapper;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
* @author admin
* @description 针对表【shop_user_coupons】的数据库操作Service实现
* @createDate 2023-10-06 23:14:17
*/
@Service
public class UserCouponsServiceImpl extends ServiceImpl<UserCouponsMapper, UserCoupons>
    implements UserCouponsService{

    @Override
    public Result<IPage<UserCouponsVo>> selectAllUC(UserCouponsDto userCouponsDto) {
        return null;
    }

    @Override
    public Result<UserCouponsVo> selectOneUC(Serializable id) {
        return null;
    }

    @Override
    public Result<UserCouponsVo> insertUc(UserCouponsDto userCouponsDto) {
        return null;
    }

    @Override
    public Result<UserCouponsVo> updateUC(UserCouponsDto userCouponsDto) {
        return null;
    }
}




