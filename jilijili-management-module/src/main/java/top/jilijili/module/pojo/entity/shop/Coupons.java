package top.jilijili.module.pojo.entity.shop;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠卷
 * @TableName shop_coupons
 */
@TableName(value ="shop_coupons")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Coupons implements Serializable {
    /**
     * 优惠券id
     */
    @TableId(value = "coupon_id")
    private String couponId;

    /**
     * 优惠券类型 (1满减、2直减、3直接白送等)
     */
    @TableField(value = "coupon_type")
    private String couponType;

    /**
     * 优惠金额
     */
    @TableField(value = "coupon_amount")
    private BigDecimal couponAmount;

    /**
     * 最小购买金额要求
     */
    @TableField(value = "min_purchase_amount")
    private BigDecimal minPurchaseAmount;

    /**
     * 有效期
     */
    @TableField(value = "expiration_date")
    private Date expirationDate;

    /**
     * 其他优惠券相关信息
     */
    @TableField(value = "other_coupon_info")
    private String otherCouponInfo;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}