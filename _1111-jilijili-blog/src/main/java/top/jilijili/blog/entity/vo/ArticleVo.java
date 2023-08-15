package top.jilijili.blog.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客文章
 *
 * @author admin
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ArticleVo implements Serializable {
    /**
     * 文章id
     */
    private String articleId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 文章顶部图像
     */
    private String picture;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 评论量
     */
    private Integer commentCount;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}