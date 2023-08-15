package top.jilijili.blog.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.system.entity.dto.SuperDto;
import top.jilijili.system.service.group.Query;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章评论表
 *
 * @author admin
 * @TableName blog_comment
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentDto extends SuperDto implements Serializable {
    /**
     * 评论id
     */

    private Long commentId;

    /**
     * 多级评论
     */
    private Long childId;

    /**
     * 被评论的博客id
     */
    @NotNull(message = "文章id不能为空",groups = Query.class)
    private Long articleId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}