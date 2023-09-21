package top.jilijili.blog.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.Article;
import top.jilijili.module.entity.Category;
import top.jilijili.module.entity.Comment;
import top.jilijili.module.entity.Tag;
import top.jilijili.module.entity.dto.ArticleDto;
import top.jilijili.module.entity.dto.CategoryDto;
import top.jilijili.module.entity.dto.CommentDto;
import top.jilijili.module.entity.dto.TagDto;
import top.jilijili.module.entity.vo.ArticleVo;
import top.jilijili.module.entity.vo.CategoryVo;
import top.jilijili.module.entity.vo.CommentVo;
import top.jilijili.module.entity.vo.TagVo;

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
