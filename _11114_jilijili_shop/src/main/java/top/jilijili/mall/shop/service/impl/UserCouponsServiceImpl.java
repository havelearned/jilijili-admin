package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.shop.mapper.UserCouponsMapper;
import top.jilijili.mall.shop.service.UserCouponsService;
import top.jilijili.module.pojo.dto.shop.UserCouponsDto;
import top.jilijili.module.pojo.entity.shop.UserCoupons;
import top.jilijili.module.pojo.vo.shop.UserCouponsVo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author admin
 * @description 针对表【shop_user_coupons】的数据库操作Service实现
 * @createDate 2023-10-06 23:14:17
 */
@Service
@AllArgsConstructor
public class UserCouponsServiceImpl extends ServiceImpl<UserCouponsMapper, UserCoupons>
        implements UserCouponsService {

    private UserCouponsMapper userCouponsMapper;


    @Override
    public Result<IPage<Map<String, Object>>> selectAllUC(Serializable userId, Integer isUsed) {
        QueryWrapper<UserCoupons> qw = new QueryWrapper<>();
        qw.eq("suc.user_id", userId);
        if (Objects.nonNull(isUsed) && isUsed <= 1) {
            qw.eq("suc.is_used", isUsed);
            if (isUsed == 1) { // 过期不可用的优惠卷
                qw.clear();
                qw.eq("suc.user_id", userId);
                qw.le("sc.expiration_date", new Date());
            } else { // 可用没有过期的优惠卷
                qw.ge("sc.expiration_date", new Date());
            }
        }
        qw.orderByDesc("sc.expiration_date");

        IPage<Map<String, Object>> result = userCouponsMapper.selectByUserId(new Page<>(0, 1000), qw);
        return Result.ok(result);
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




