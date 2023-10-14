package top.jilijili.module.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

/**
 * 用户优惠卷映射类
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserWithCouponsVo {
    private Long userCouponId;
    private Boolean isUsed;
    private Date usageDate;

    private Long userId;
    private String nickname;
    private String avatar;
    private List<CouponsVo> coupons;


}
