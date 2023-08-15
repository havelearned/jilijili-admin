package top.jilijili.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【blog_article(博客文章)】的数据库操作Mapper
* @createDate 2023-08-13 23:19:57
* @Entity top.jilijili.blog.entity.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}




