package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品分类表
 *
 * @TableName shop_categories
 */
@TableName(value = "shop_categories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Categories implements Serializable {
    /**
     * 分类ID
     */
    @TableId(value = "category_id")
    private Long categoryId;

    /**
     * 分类名称
     */
    @TableField(value = "category_name")
    private String categoryName;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}