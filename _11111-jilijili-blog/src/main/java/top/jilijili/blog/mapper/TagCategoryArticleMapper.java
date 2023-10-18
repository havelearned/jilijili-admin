package top.jilijili.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.pojo.entity.blog.TagCategoryArticle;

/**
* @author admin
* @description 针对表【blog_tag_category_article(分类,标签和文章关联表)】的数据库操作Mapper
* @createDate 2023-08-13 23:19:57
* @Entity top.jilijili.blog.entity.TagCategoryArticle
*/
@Mapper
public interface TagCategoryArticleMapper extends BaseMapper<TagCategoryArticle> {

}




