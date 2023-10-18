package top.jilijili.module.pojo.vo.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

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
public class CategoryVo implements Serializable {
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
    private Integer categoryOrder;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}