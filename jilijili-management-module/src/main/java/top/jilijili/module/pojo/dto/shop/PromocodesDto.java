package top.jilijili.module.pojo.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;
import top.jilijili.common.group.Insert;
import top.jilijili.module.pojo.dto.SuperDto;

import java.util.Date;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PromocodesDto extends SuperDto {
    /**
     * 兑换码id
     */
    private Long promoCodeId;

    /**
     * 关联的优惠券id (可选)
     */
    private Long couponId;

    /**
     * 店铺id,表示使用对象,为空则是<通用>兑换码(可选)
     */
    private Long shopId;

    /**
     * 兑换码,key开头 需要数据脱敏
     */
    private String promoCode;

    /**
     * 是否已使用 (0 - 未使用, 1 - 已使用)
     */
    private Integer isUsed;

    /**
     * 生成批次,gen开头
     */
    private String genCount;

    /**
     * 批次数: 10,50,100,300,500
     */
    @Range(min = 10, max = 10000, message = "生成数量:大于等于10,小于等于10000", groups = Insert.class)
    private Integer batchCount;

    /**
     * 有效期
     */
    private Date expirationDate;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}