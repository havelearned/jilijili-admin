package top.jilijili.module.pojo.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.jeecgframework.poi.excel.annotation.Excel;
import top.jilijili.module.pojo.dto.SuperDto;

import java.util.Date;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PromocodesVo extends SuperDto {
    /**
     * 兑换码id
     */
    private String promoCodeId;

    /**
     * 关联的优惠券id (可选)
     */
    private String couponId;

    /**
     * 店铺id,表示使用对象,为空则是<通用>兑换码(可选)
     */
    private Long shopId;

    /**
     * 兑换码,key开头 需要数据脱敏
     */
    @Excel(name = "兑换码", width = 40)
    private String promoCode;

    /**
     * 是否已使用 (0 - 未使用, 1 - 已使用)
     */
    @Excel(name = "兑换码", width = 20,replace = { "已使用_1","未使用_0"})
    private Integer isUsed;

    /**
     * 生成批次,gen开头
     */
    @Excel(name = "生成批次", width = 20)
    private String genCount;

    /**
     * 有效期
     */
    @Excel(name = "过期时间", width = 20,format = "yyyy-MM-dd")
    private Date expirationDate;

    // 是否过期
    private Integer isExpiration;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 优惠卷
     */
    private CouponsVo coupons;

}