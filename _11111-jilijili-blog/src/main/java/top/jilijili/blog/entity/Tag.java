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
 * 博客标签
 * @author admin
 * @TableName blog_tag
 */
@TableName(value ="blog_tag")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Tag implements Serializable {
    /**
     * 标签id
     */
    @TableId(value = "tag_id")
    private Long tagId;

    /**
     * 多级标签
     */
    @TableField(value = "child_id")
    private Long childId;

    /**
     * 排序
     */
    @TableField(value = "order")
    private Integer order;

    /**
     * 标签名称
     */
    @TableField(value = "tag_title")
    private String tagTitle;

    /**
     * 创建时间
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}