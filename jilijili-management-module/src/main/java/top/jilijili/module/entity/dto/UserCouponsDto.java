package top.jilijili.module.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserCouponsDto extends SuperDto implements Serializable {
    /**
     * 用户优惠券id
     */
    private Long userCouponId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 是否已使用 (0 - 未使用, 1 - 已使用)
     */
    private Integer isUsed;

    /**
     * 使用日期 (可选)
     */
    private Date usageDate;

}