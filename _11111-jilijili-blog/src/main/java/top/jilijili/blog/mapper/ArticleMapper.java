package top.jilijili.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.entity.Article;
import top.jilijili.module.entity.vo.ArticleVo;
import top.jilijili.module.entity.vo.TagVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @description 针对表【blog_article(博客文章)】的数据库操作Mapper
 * @createDate 2023-08-13 23:19:57
 * @Entity top.jilijili.blog.entity.Article
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {


    /**
     * 通过{用户id}查询用户文章年份
     *
     * @param userId
     * @return
     */
    @MapKey("year")
    List<ArticleVo> queryByUserIdYear(@Param("userId") Serializable userId);


    /**
     * 通过一个或者多个文章id查询文章信息
     *
     * @param page
     * @param size
     * @param articleIds
     * @return
     */
    List<ArticleVo> queryByArticleIdsList(@Param("page") Serializable page,
                                          @Param("size") Serializable size,
                                          @Param("articleIds") List<Long> articleIds);

    /**
     * 查询标签下的所有博客
     *
     * @param page
     * @param size
     * @param tagId
     * @return
     */
    List<ArticleVo> queryArticleByTagId(@Param("page") Serializable page,
                                        @Param("size") Serializable size,
                                        @Param("tagId") Serializable tagId);

    /**
     * 通过分类id查询文章信息
     *
     * @param categoryId
     * @return
     */
    List<ArticleVo> queryArticleByCategoryId(@Param("page") Serializable page,
                                             @Param("size") Serializable size,
                                             @Param("categoryId") Serializable categoryId);

    /**
     * 通过文章id查询该文章下的所有标签
     * @param articleId
     * @return
     */
    List<TagVo> queryTagsByArticleId(@Param("articleId") Serializable articleId);

}




