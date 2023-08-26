package top.jilijili.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.blog.entity.Article;
import top.jilijili.blog.entity.vo.ArticleVo;

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

    List<ArticleVo> queryByArticleIdsList(@Param("page") Serializable page,
                                          @Param("size") Serializable size,
                                          @Param("articleIds") List<Long> articleIds);


}




