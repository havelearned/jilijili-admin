package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 * @TableName shop_products
 */
@TableName(value ="shop_products")
@Data
public class Products implements Serializable {
    /**
     * 商品ID
     */
    @TableId(value = "product_id",type = IdType.AUTO)
    private Long productId;

    /**
     * 分类ID
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 商品名称
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 商品描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 库存数量
     */
    @TableField(value = "stock_quantity")
    private Integer stockQuantity;

    /**
     * 图片链接
     */
    @TableField(value = "image_url")
    private String imageUrl;

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