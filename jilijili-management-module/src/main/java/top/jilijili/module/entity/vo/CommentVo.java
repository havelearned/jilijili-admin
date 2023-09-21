package top.jilijili.module.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class CommentVo implements Serializable {


    private List<CommentVo> childList;

    /**
     * 评论id
     */
    private String commentId;

    /**
     * 多级评论
     */
    private String childId;

    private Integer commentStatus;


    /**
     * 评论内容
     */
    private String content;


    private String articleId;
    private String userId;
    private String nickname;
    private String avatar;
    private Integer gender;
    private String lastLoginIp;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}