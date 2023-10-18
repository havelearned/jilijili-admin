package top.jilijili.module.pojo.dto.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.common.entity.SuperDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车表
 *
 * @TableName shop_carts
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@Data
public class CartsDto extends SuperDto implements Serializable {
    /**
     * 购物车ID
     */
    private Long cartId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}