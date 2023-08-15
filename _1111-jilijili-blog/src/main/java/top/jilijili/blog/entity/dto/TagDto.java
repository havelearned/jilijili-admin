package top.jilijili.blog.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.system.entity.dto.SuperDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客标签
 *
 * @author admin
 * @TableName blog_tag
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TagDto extends SuperDto implements Serializable {
    /**
     * 标签id
     */

    private String tagId;

    /**
     * 多级标签
     */

    private String childId;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 标签名称
     */
    private String tagTitle;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;


}