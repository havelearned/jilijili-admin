package top.jilijili.module.pojo.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.common.entity.SuperDto;

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
    private Long tagId;

    /**
     * 多级标签
     */

    private Long childId;


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