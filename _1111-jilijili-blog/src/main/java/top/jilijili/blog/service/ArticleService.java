package top.jilijili.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.jilijili.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.blog.entity.dto.ArticleDto;
import top.jilijili.blog.entity.vo.ArticleVo;

import java.util.List;

/**
* @author admin
* @description 针对表【blog_article(博客文章)】的数据库操作Service
* @createDate 2023-08-13 23:19:57
*/
public interface ArticleService extends IService<Article> {

    /**
     * 分页查询
     * @param articleDto 查询条件
     * @return list
     */
    IPage<ArticleVo> pageList(ArticleDto articleDto);

    /**
     * 保存文章
     * @param articleDto 文章内容
     * @return vo
     */
    ArticleVo saveArticleAndCategoryAndTag(ArticleDto articleDto);

    /**
     * 更新文章
     * @param articleDto
     * @return
     */
    ArticleVo updateArticleAndCategoryAndTag(ArticleDto articleDto);

    /**
     * 批量删除
     * @param idList
     * @return
     */
    Boolean removeByArticle(List<Long> idList);
}
