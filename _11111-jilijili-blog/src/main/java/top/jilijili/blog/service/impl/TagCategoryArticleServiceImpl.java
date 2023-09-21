package top.jilijili.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.module.entity.TagCategoryArticle;
import top.jilijili.blog.service.TagCategoryArticleService;
import top.jilijili.blog.mapper.TagCategoryArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【blog_tag_category_article(分类,标签和文章关联表)】的数据库操作Service实现
* @createDate 2023-08-13 23:19:57
*/
@Service
public class TagCategoryArticleServiceImpl extends ServiceImpl<TagCategoryArticleMapper, TagCategoryArticle>
    implements TagCategoryArticleService{

}




