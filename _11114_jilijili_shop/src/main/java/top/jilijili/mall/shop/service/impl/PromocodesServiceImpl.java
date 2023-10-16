package top.jilijili.mall.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.jilijili.common.entity.Result;
import top.jilijili.common.heandler.JiliException;
import top.jilijili.mall.shop.mapper.ConvertMapper;
import top.jilijili.mall.shop.service.PromocodesService;
import top.jilijili.module.entity.Promocodes;
import top.jilijili.module.entity.dto.PromocodesDto;
import top.jilijili.module.entity.vo.PromocodesVo;
import top.jilijili.mall.shop.mapper.PromocodesMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author admin
 * @description 针对表【shop_promocodes】的数据库操作Service实现
 * @createDate 2023-10-06 23:14:17
 */
@Service
@AllArgsConstructor
public class PromocodesServiceImpl extends ServiceImpl<PromocodesMapper, Promocodes>
        implements PromocodesService {
    private ConvertMapper convertMapper;
    private PromocodesMapper promocodesMapper;

    private static final String KYE = "KEY";
    private static final String GEN = "GEN";

    @Override
    public IPage<PromocodesVo> selectAllPromocodes(PromocodesDto promocodesDto) {
        IPage<PromocodesVo> page = new Page<>(promocodesDto.getPage(), promocodesDto.getSize());
        QueryWrapper<PromocodesVo> queryWrapper = new QueryWrapper<>();

        queryWrapper
                .eq(promocodesDto.getPromoCodeId() != null, "sp.promo_code_id", promocodesDto.getPromoCodeId())
                .eq(promocodesDto.getCouponId() != null, "sp.coupon_id", promocodesDto.getCouponId())
                .eq(promocodesDto.getShopId() != null, "sp.shop_id", promocodesDto.getShopId())
                .eq(StringUtils.hasText(promocodesDto.getPromoCode()), "sp.promo_code", promocodesDto.getPromoCode())
                .eq(promocodesDto.getIsUsed() != null, "sp.is_used", promocodesDto.getIsUsed())
                .eq(StringUtils.hasText(promocodesDto.getGenCount()), "sp.gen_count", promocodesDto.getGenCount())
                .between(promocodesDto.getExpirationDate() != null && promocodesDto.getComparisonTime() != null,
                        "sp.expiration_date", promocodesDto.getExpirationDate(), promocodesDto.getComparisonTime())
                .between(promocodesDto.getCreatedTime() != null && promocodesDto.getComparisonTime() != null,
                        "sp.created_time", promocodesDto.getCreatedTime(), promocodesDto.getComparisonTime())
                .orderByDesc("sp.created_time");
        page = promocodesMapper.selectAllPromocodes(page, queryWrapper);

        // 过期检测
        long currentTimestamp = System.currentTimeMillis();
        page.convert(item -> item.setIsExpiration(isExpiration(currentTimestamp, item.getExpirationDate().getTime())));

        return page;
    }


    @Override
    public Result<PromocodesVo> selectOnePromocodes(Serializable id) {
        PromocodesVo promoCodesVo = this.convertMapper.toPromoCodesVo(this.getById(id));
        if (Objects.nonNull(promoCodesVo)) {
            promoCodesVo.setIsExpiration(isExpiration(System.currentTimeMillis(),
                    promoCodesVo.getExpirationDate().getTime()));
        }
        return Result.ok(promoCodesVo);
    }

    @Override
    public Result<PromocodesVo> insertPromocodes(PromocodesDto promocodesDto) {
        Promocodes promoCodes = this.convertMapper.toPromoCodes(promocodesDto);
        if (Objects.nonNull(promoCodes)) {
            promoCodes.setPromoCode(this.genKey());
            promoCodes.setGenCount(this.genGenCount());
        }
        boolean saved = this.save(promoCodes);
        PromocodesVo promoCodesVo = this.convertMapper.toPromoCodesVo(promoCodes);
        return saved ? Result.ok(promoCodesVo, "操作成功") : Result.ok(promoCodesVo, "操作失败");
    }

    @Override
    public Result<PromocodesVo> updatePromocodes(PromocodesDto promocodesDto) {
        Promocodes promoCodes = this.convertMapper.toPromoCodes(promocodesDto);
        boolean updated = this.updateById(promoCodes);
        PromocodesVo promoCodesVo = this.convertMapper.toPromoCodesVo(promoCodes);
        return updated ? Result.ok(promoCodesVo, "操作成功") : Result.ok(promoCodesVo, "操作失败");
    }

    @Transactional(rollbackFor = JiliException.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Result<List<Promocodes>> batchPromocodes(PromocodesDto promocodesDto) {
        if (promocodesDto.getBatchCount() < 10 || promocodesDto.getBatchCount() >= 10001) {
            return Result.fail(null, "操作失败! 生成数量太少或者太多,10~10000之间");
        }
        // 生成对兑换码
        List<Promocodes> saveList = new ArrayList<>();
        for (int i = 0; i < promocodesDto.getBatchCount(); i++) {
            Promocodes promocodes = new Promocodes();
            promocodes.setExpirationDate(promocodesDto.getExpirationDate());
            promocodes.setGenCount(genGenCount());
            promocodes.setPromoCode(genKey());
            saveList.add(promocodes);
        }
        // 批量持久化数据库,每次1000条
        return this.saveBatch(saveList, 1000) ? Result.ok(saveList, "操作成功") : Result.fail("操作失败");
    }


    /**
     * @param currentTimestamp    当前时间戳
     * @param expirationTimestamp 比较的时间戳
     * @return 0 未过期,1 已过期
     */
    private int isExpiration(long currentTimestamp, long expirationTimestamp) {
        return currentTimestamp > expirationTimestamp ? 1 : 0;
    }


    /**
     * 生成批次
     *
     * @return
     */
    private String genGenCount() {
        // 获取当前年份和月份
        int year = DateUtil.year(DateUtil.date());
        int month = DateUtil.month(DateUtil.date()) + 1; // 月份从0开始，所以需要加1

        // 生成随机字符串
        String randomString = RandomUtil.randomString(32); // 生成32位随机字符串，可以根据需求调整长度

        // 组合生成的key
        return GEN + year + month + randomString;
    }

    /**
     * 生成兑换码
     *
     * @return KEY 开头的兑换码
     */
    private String genKey() {
        // 获取当前年份和月份
        int year = DateUtil.year(DateUtil.date());
        int month = DateUtil.month(DateUtil.date()) + 1; // 月份从0开始，所以需要加1

        String randomString = RandomUtil.randomString(32); // 生成32位随机字符串，可以根据需求调整长度

        String uuid = RandomUtil.randomString(36);

        // 组合生成的key
        return KYE + year + month + randomString + uuid;
    }
}




