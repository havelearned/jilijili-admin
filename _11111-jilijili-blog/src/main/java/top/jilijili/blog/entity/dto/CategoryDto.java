package top.jilijili.blog.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.common.entity.SuperDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章分类
 *
 * @author admin
 * @TableName blog_category
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryDto extends SuperDto implements Serializable {
    /**
     * 分类id
     */

    private String  categoryId;

    /**
     * 多级分类
     */

    private String childId;

    /**
     * 分类名称
     */

    private String title;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}