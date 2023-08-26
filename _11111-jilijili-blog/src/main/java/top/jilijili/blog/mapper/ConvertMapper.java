package top.jilijili.blog.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.blog.entity.Article;
import top.jilijili.blog.entity.Category;
import top.jilijili.blog.entity.Comment;
import top.jilijili.blog.entity.Tag;
import top.jilijili.blog.entity.dto.ArticleDto;
import top.jilijili.blog.entity.dto.CategoryDto;
import top.jilijili.blog.entity.dto.CommentDto;
import top.jilijili.blog.entity.dto.TagDto;
import top.jilijili.blog.entity.vo.ArticleVo;
import top.jilijili.blog.entity.vo.CategoryVo;
import top.jilijili.blog.entity.vo.CommentVo;
import top.jilijili.blog.entity.vo.TagVo;

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
