package top.jilijili.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章分类
 * @author admin
 * @TableName blog_category
 */
@TableName(value ="blog_category")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Category implements Serializable {
    /**
     * 分类id
     */
    @TableId(value = "category_id")
    private Long categoryId;

    /**
     * 多级分类
     */
    @TableField(value = "child_id")
    private Long childId;

    /**
     * 分类名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 排序
     */
    @TableField(value = "order")
    private Integer order;

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