package top.jilijili.module.pojo.entity.shop;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.module.pojo.dto.SuperDto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @TableName shop_user_ratings
 */
@TableName(value = "shop_user_ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class UserRatings extends SuperDto implements Serializable {
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 物品id
     */
    @TableField(value = "item_id")
    private String itemId;

    /**
     * 评分1.0-5.0
     */
    @TableField(value = "rating")
    private BigDecimal rating;

    /**
     * 时间戳
     */
    @TableField(value = "timestamp")
    private Long timestamp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}