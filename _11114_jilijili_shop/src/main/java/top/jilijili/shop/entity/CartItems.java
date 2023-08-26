package top.jilijili.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 购物车明细表
 * @TableName shop_cart_items
 */
@TableName(value ="shop_cart_items")
@Data
public class CartItems implements Serializable {
    /**
     * 明细ID
     */
    @TableId(value = "item_id")
    private Integer itemId;

    /**
     * 购物车ID
     */
    @TableField(value = "cart_id")
    private Integer cartId;

    /**
     * 商品ID
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 数量
     */
    @TableField(value = "quantity")
    private Integer quantity;

    /**
     * 小计金额
     */
    @TableField(value = "subtotal")
    private BigDecimal subtotal;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}