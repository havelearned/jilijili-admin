package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.common.entity.Result;
import top.jilijili.mall.shop.mapper.ConvertMapper;
import top.jilijili.mall.shop.mapper.CouponsMapper;
import top.jilijili.mall.shop.service.CouponsService;
import top.jilijili.module.pojo.dto.shop.CouponsDto;
import top.jilijili.module.pojo.dto.sys.SysUserDto;
import top.jilijili.module.pojo.entity.shop.Coupons;
import top.jilijili.module.pojo.vo.shop.CouponsVo;
import top.jilijili.module.pojo.vo.shop.UserWithCouponsVo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author admin
 * @description 针对表【shop_coupons】的数据库操作Service实现
 * @createDate 2023-10-06 23:14:17
 */
@Service
@AllArgsConstructor
public class CouponsServiceImpl extends ServiceImpl<CouponsMapper, Coupons>
        implements CouponsService {
    private ConvertMapper convertMapper;
    private CouponsMapper couponsMapper;


    @Override
    public Result<IPage<UserWithCouponsVo>> selectAllUserCoupons(CouponsDto couponsDto) {
        IPage<UserWithCouponsVo> page = new Page<>(couponsDto.getPage(), couponsDto.getSize());
        QueryWrapper<CouponsVo> queryWrapper = new QueryWrapper<>();

        queryWrapper
                // 通过优惠卷Id查
                .eq(couponsDto.getCouponId() != null, "sc.promo_code_id", couponsDto.getCouponId())
                // 通过优惠卷类型查询
                .eq(couponsDto.getCouponType() != null, "sc.coupon_type", couponsDto.getCouponType())
                // 通过优惠卷面额大小查
                .between(couponsDto.getMin() != null && couponsDto.getMax() != null,
                        "sc.coupon_amount", couponsDto.getMin(), couponsDto.getMax())
                // 通过优惠卷过期时间段查
                .between(couponsDto.getExpirationDate() != null && couponsDto.getComparisonTime() != null,
                        "sc.expiration_date", couponsDto.getExpirationDate(), couponsDto.getComparisonTime())
                // 通过用户使用情况查询
                .eq(couponsDto.getIsUsed() != null, "suc.coupon_type", couponsDto.getIsUsed())
                // 通过优惠卷类介绍查
                .like(StringUtils.hasText(couponsDto.getOtherCouponInfo()), "sc.other_coupon_info", couponsDto.getOtherCouponInfo())
                .orderByDesc("sc.expiration_date");
        SysUserDto sysUserDto = couponsDto.getSysUserDto();
        if (Objects.nonNull(sysUserDto)) {
            queryWrapper
                    // 通过用户id查询
                    .eq(Objects.nonNull(sysUserDto.getUserId()), "su.user_id", couponsDto.getSysUserDto().getUserId())
                    // 通过用户昵称查询
                    .like(StringUtils.hasText(sysUserDto.getNickname()), "su.nickname", sysUserDto.getNickname());
        }
        page = couponsMapper.selectAllCoupons(page, queryWrapper);


        return Result.ok(page);
    }

    @Override
    public Result<CouponsVo> selectOneCoupons(Serializable id) {

        return Result.ok(this.convertMapper.toCouponsVo(this.getById(id)));
    }

    @Override
    public Result<CouponsVo> insertCoupons(CouponsDto couponsDto) {
        Coupons coupons = this.convertMapper.toCoupons(couponsDto);

            return this.save(coupons) ?
                    Result.ok(this.convertMapper.toCouponsVo(coupons), "操作成功") : Result.fail(null, "操作失败");
    }

    @Override
    public Result<CouponsVo> updateCoupons(CouponsDto couponsDto) {
        Coupons coupons = this.convertMapper.toCoupons(couponsDto);
        return this.updateById(coupons) ?
                Result.ok(this.convertMapper.toCouponsVo(coupons), "操作成功") : Result.fail(null, "操作失败");
    }


    @Override
    public Result<Page<Coupons>> selectAllCoupons(CouponsDto coupons) {
        Page<Coupons> page = this.lambdaQuery()
                .eq(Objects.nonNull(coupons.getCouponId()), Coupons::getCouponId, coupons.getCouponId())
                .eq(coupons.getCouponType() != null, Coupons::getCouponType, coupons.getCouponType())
                .like(StringUtils.hasText(coupons.getOtherCouponInfo()), Coupons::getOtherCouponInfo, coupons.getOtherCouponInfo())
                .between(coupons.getMin() != null && coupons.getMax() != null,
                        Coupons::getCouponAmount, coupons.getMin(), coupons.getMax())
                .between(coupons.getExpirationDate() != null && coupons.getComparisonTime() != null,
                        Coupons::getExpirationDate, coupons.getExpirationDate(), coupons.getComparisonTime())
                .page(new Page<>(coupons.getPage(), coupons.getSize()));
        return Result.ok(page);
    }
}




