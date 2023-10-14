package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName shop_promocodes
 */
@TableName(value ="shop_promocodes")
@Data
public class Promocodes implements Serializable {
    /**
     * 兑换码id
     */
    @TableId(value = "promo_code_id")
    private Long promoCodeId;

    /**
     * 关联的优惠券id (可选)
     */
    @TableField(value = "coupon_id")
    private Long couponId;

    /**
     * 店铺id,表示使用对象,为空则是<通用>兑换码(可选)
     */
    @TableField(value = "shop_id")
    private Long shopId;

    /**
     * 兑换码,key开头
     */
    @TableField(value = "promo_code")
    private String promoCode;

    /**
     * 是否已使用 (0 - 未使用, 1 - 已使用)
     */
    @TableField(value = "is_used")
    private Integer isUsed;

    /**
     * 生成批次,gen开头
     */
    @TableField(value = "gen_count")
    private String genCount;

    /**
     * 有效期
     */
    @TableField(value = "expiration_date")
    private Date expirationDate;

    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time",fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}