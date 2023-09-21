package top.jilijili.module.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class CartsVo implements Serializable {


    /**
     * 明细ID
     */
    private String itemId;

    /**
     * 购物车ID
     */
    private String cartId;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 小计金额
     */
    private BigDecimal subtotal;

    /**
     * 创建时间
     */
    private Date createdTime;


    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 图片链接
     */
    private String imageUrl;

}