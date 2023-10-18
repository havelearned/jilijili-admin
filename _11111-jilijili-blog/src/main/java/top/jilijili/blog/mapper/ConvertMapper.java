package top.jilijili.blog.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.module.pojo.entity.blog.Article;
import top.jilijili.module.pojo.entity.blog.Category;
import top.jilijili.module.pojo.entity.blog.Comment;
import top.jilijili.module.pojo.entity.blog.Tag;
import top.jilijili.module.pojo.dto.blog.ArticleDto;
import top.jilijili.module.pojo.dto.blog.CategoryDto;
import top.jilijili.module.pojo.dto.blog.CommentDto;
import top.jilijili.module.pojo.dto.blog.TagDto;
import top.jilijili.module.pojo.vo.blog.ArticleVo;
import top.jilijili.module.pojo.vo.blog.CategoryVo;
import top.jilijili.module.pojo.vo.blog.CommentVo;
import top.jilijili.module.pojo.vo.blog.TagVo;

/**
 * @author Amani
 * @date 2023年06月22日 13:41
 */
@Mapper(componentModel = "spring")
@Component
public interface ConvertMapper {
    Article toArticle(ArticleDto articleDto);

    ArticleVo toArticleVo(Article article);

    Tag toTag(TagDto tagDto);

    TagVo toTagVo(Tag tag);

    Category toCategory(CategoryDto categoryDto);

    CategoryVo toCategoryVo(Category category);

    Comment toComment(CommentDto commentDto);

    CommentVo toCommentVo(Comment comment);
}
