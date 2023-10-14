package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName shop_user_coupons
 */
@TableName(value ="shop_user_coupons")
@Data
public class UserCoupons implements Serializable {
    /**
     * 用户优惠券id
     */
    @TableId(value = "user_coupon_id")
    private Long userCouponId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 优惠券id
     */
    @TableField(value = "coupon_id")
    private Long couponId;

    /**
     * 是否已使用 (0 - 未使用, 1 - 已使用)
     */
    @TableField(value = "is_used")
    private Integer isUsed;

    /**
     * 使用日期 (可选)
     */
    @TableField(value = "usage_date")
    private Date usageDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}