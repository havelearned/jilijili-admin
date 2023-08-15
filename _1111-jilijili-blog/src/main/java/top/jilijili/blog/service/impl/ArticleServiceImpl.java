package top.jilijili.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.jilijili.blog.entity.Article;
import top.jilijili.blog.entity.TagCategoryArticle;
import top.jilijili.blog.entity.dto.ArticleDto;
import top.jilijili.blog.entity.vo.ArticleVo;
import top.jilijili.blog.mapper.ArticleMapper;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.ArticleService;
import top.jilijili.blog.service.TagCategoryArticleService;

import java.util.List;

/**
 * @author admin
 * @description 针对表【blog_article(博客文章)】的数据库操作Service实现
 * @createDate 2023-08-13 23:19:57
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    private ConvertMapper convertMapper;
    private ArticleMapper articleMapper;
    private TagCategoryArticleService tagCategoryArticleService;

    @Override
    public IPage<ArticleVo> pageList(ArticleDto articleDto) {
        IPage<Article> page = new Page<>(articleDto.getPage(), articleDto.getSize());
        Article article = this.convertMapper.toArticle(articleDto);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>(article);
        page = this.page(page, queryWrapper);
        return page.convert(this.convertMapper::toArticleVo);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public ArticleVo saveArticleAndCategoryAndTag(ArticleDto articleDto) {
        // 保存文章
        Article article = this.convertMapper.toArticle(articleDto);
        boolean save = this.save(article);
        if (save) {
            // 保存关联分类
            TagCategoryArticle build = TagCategoryArticle.builder()
                    .categoryId(Long.valueOf(articleDto.getCategoryId()))
                    .articleId(article.getArticleId()).build();
            boolean saved = this.tagCategoryArticleService.save(build);
            if (saved && (saveBatchByTagCatgoryArticle(articleDto, article))) {
                return this.convertMapper.toArticleVo(article);
            }

        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public ArticleVo updateArticleAndCategoryAndTag(ArticleDto articleDto) {
        Article article = this.convertMapper.toArticle(articleDto);
        boolean update = this.updateById(article);
        if (update) {
            // 删除 关联标签,和分类
            LambdaQueryWrapper<TagCategoryArticle> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(TagCategoryArticle::getArticleId, article.getArticleId());
            this.tagCategoryArticleService.remove(queryWrapper);

            TagCategoryArticle build = TagCategoryArticle.builder()
                    .articleId(article.getArticleId())
                    .tagId(null)
                    .categoryId(Long.valueOf(articleDto.getCategoryId())).build();
            this.tagCategoryArticleService.save(build);

            // 保存关联的标签
            if (saveBatchByTagCatgoryArticle(articleDto, article)) {
                return this.convertMapper.toArticleVo(article);
            }

        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public Boolean removeByArticle(List<Long> idList) {
        boolean removed = this.removeByIds(idList);
        if (removed) {
            this.tagCategoryArticleService.remove(new LambdaQueryWrapper<TagCategoryArticle>()
                    .in(TagCategoryArticle::getArticleId, idList));
        }
        return removed;
    }

    /**
     * 批量保存文章关联的标签
     *
     * @param articleDto
     * @param article
     * @return 保存成功?
     */
    private boolean saveBatchByTagCatgoryArticle(ArticleDto articleDto, Article article) {
        List<TagCategoryArticle> list = articleDto.getTagId().stream().map(tagId ->
                new TagCategoryArticle()
                        .setArticleId(article.getArticleId())
                        .setCategoryId(null)
                        .setTagId(Long.valueOf(tagId))).toList();
        return this.tagCategoryArticleService.saveBatch(list);
    }
}




