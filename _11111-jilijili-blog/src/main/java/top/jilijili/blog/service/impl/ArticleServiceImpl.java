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
import top.jilijili.blog.entity.vo.TagVo;
import top.jilijili.blog.mapper.ArticleMapper;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.ArticleService;
import top.jilijili.blog.service.TagCategoryArticleService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<ArticleVo> queryArticleByTagId(Serializable page, Serializable size, Serializable tagId) {
        return this.articleMapper.queryArticleByTagId(page, size, tagId);
    }

    @Override
    public List<ArticleVo> queryArticleByCategoryId(Serializable page, Serializable size, Serializable categoryId) {
        return this.articleMapper.queryArticleByCategoryId(page, size, categoryId);
    }

    @Override
    public List<TagVo> queryTagsByArticleId(Serializable articleId) {
        return this.articleMapper.queryTagsByArticleId(articleId);
    }

    @Override
    public Map<String, List<ArticleVo>> selectYearArticleMap(ArticleDto articleDto) {
        // 查询用户文章年份,筛选出文章id
        List<ArticleVo> articleVos = this.articleMapper.queryByUserIdYear(articleDto.getUserId());
        Map<String, List<ArticleVo>> articleVoMap = new HashMap<>();
        // 通过某一年分的多个文章id查询文章信息
        for (ArticleVo articleVo : articleVos) {
            List<Long> list = Arrays.stream(articleVo.getArticleIds().split(",")).map(Long::valueOf).toList();
            List<ArticleVo> vos = this.articleMapper.queryByArticleIdsList(articleDto.getPage(), articleDto.getSize(), list);
            if (vos.isEmpty()) {
                continue;
            }
            articleVoMap.put(articleVo.getYear(), vos);
        }
        return articleVoMap;
    }

    @Override
    public IPage<ArticleVo> pageList(ArticleDto articleDto) {
        IPage<Article> page = new Page<>(articleDto.getPage(), articleDto.getSize());
        Article article = this.convertMapper.toArticle(articleDto);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>(article);
        page = this.page(page, queryWrapper);
        // TODO 返回前端 [1]分类内容 [2]标签列表
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
            if (saved && (saveBatchByTagCategoryArticle(articleDto, article))) {
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
            if (saveBatchByTagCategoryArticle(articleDto, article)) {
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
    private boolean saveBatchByTagCategoryArticle(ArticleDto articleDto, Article article) {
        List<TagCategoryArticle> list = articleDto.getTagId().stream().map(tagId ->
                new TagCategoryArticle()
                        .setArticleId(article.getArticleId())
                        .setCategoryId(null)
                        .setTagId(Long.valueOf(tagId))).toList();
        return this.tagCategoryArticleService.saveBatch(list);
    }
}




