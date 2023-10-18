package top.jilijili.module.pojo.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CouponsVo  implements Serializable {
    /**
     * 优惠券id
     */
    private String couponId;

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

}