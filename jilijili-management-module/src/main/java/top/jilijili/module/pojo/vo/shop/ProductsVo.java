package top.jilijili.module.pojo.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.module.pojo.entity.shop.Categories;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 *
 * @TableName shop_products
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@Data
public class ProductsVo implements Serializable {
    /**
     * 商品ID
     */
    private String productId;

    /**
     * 分类ID
     */
    private String categoryId;
    /**
     * 商品分类
     */
    private Categories categories;

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
     * 库存数量
     */
    private Integer stockQuantity;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}