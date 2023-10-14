package top.jilijili.module.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName shop_coupons
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CouponsDto extends SuperDto implements Serializable {
    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 优惠券类型 (1满减、2直减、3直接白送等)
     */
    private Integer couponType;

    /**
     * 优惠金额
     */
    private BigDecimal couponAmount;

    /**
     * 最小购买金额要求
     */
    private BigDecimal minPurchaseAmount;

    /**
     * 有效期
     */
    private Date expirationDate;

    /**
     * 其他优惠券相关信息
     */
    private String otherCouponInfo;


    private Integer min;
    private Integer max;

    /**
     *  用户查询信息
     */
    private SysUserDto sysUserDto;

    private Integer isUsed;

}