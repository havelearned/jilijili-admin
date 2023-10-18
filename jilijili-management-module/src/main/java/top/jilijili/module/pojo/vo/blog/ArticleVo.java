package top.jilijili.module.pojo.vo.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.common.entity.Item;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 分类Id
     */
    private String categoryId;

    /**
     * 用户昵称
     */
    private String nickname;

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


    /**
     * 年份查询
     */
    private String year;

    /**
     * 多个文章Id
     */
    private String articleIds;


    /**
     * 文章的多个标签
     */
    private List<Item> tagId;
}