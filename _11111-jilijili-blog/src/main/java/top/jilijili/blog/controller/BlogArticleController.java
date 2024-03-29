package top.jilijili.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.jilijili.module.pojo.dto.blog.ArticleDto;
import top.jilijili.module.pojo.dto.blog.CategoryDto;
import top.jilijili.module.pojo.dto.blog.TagDto;
import top.jilijili.module.pojo.vo.blog.ArticleVo;
import top.jilijili.module.pojo.vo.blog.TagVo;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.ArticleService;
import top.jilijili.blog.service.TagCategoryArticleService;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 文章管理
 *
 * @author makejava
 * @since 2023-08-13 23:22:12
 */
@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class BlogArticleController extends SuperController {
    /**
     * 服务对象
     */
    private ArticleService articleService;
    private ConvertMapper convertMapper;
    private TagCategoryArticleService tagCategoryArticleService;


    // TODO 浏览量,评论量维护


    /**
     * 通过分类id查询文章信息
     *
     * @param categoryDto
     * @return
     */
    @GetMapping("/articleByCategoryId")
    public Result<List<ArticleVo>> selectArticleByCategoryId(CategoryDto categoryDto) {
        return Result.ok(this.articleService.queryArticleByCategoryId(
                categoryDto.getPage(), categoryDto.getSize(), categoryDto.getCategoryId()));
    }


    /**
     * 查询标签下的所有博客
     *
     * @param tagDto 使用分页所以使用Dto
     * @return
     */
    @GetMapping("/articleByTagId")
    public Result<List<ArticleVo>> selectArticleByTagId(TagDto tagDto) {
        return Result.ok(this.articleService.queryArticleByTagId(tagDto.getPage(), tagDto.getSize(), tagDto.getTagId()));
    }

    /**
     * 通过文章id查询该文章下的所有标签
     *
     * @param articleId
     * @return
     */
    @GetMapping("/tagsByArticleId/{articleId}")
    public Result<List<TagVo>> selectTagsByArticleId(@PathVariable("articleId") Long articleId) {
        return Result.ok(this.articleService.queryTagsByArticleId(articleId));
    }


    /**
     * 查询文章的时间轴
     *
     * @param articleDto
     * @return
     */
    @GetMapping("/timeline")
    public Result<Map<String, List<ArticleVo>>> queryTimeLine(ArticleDto articleDto) {
        return Result.ok(this.articleService.selectYearArticleMap(articleDto));

    }

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<ArticleVo>> selectAll(ArticleDto articleDto) {
        IPage<ArticleVo> convert = this.articleService.pageList(articleDto);
        return Result.ok(convert);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<ArticleVo> selectOne(@PathVariable Serializable id) {
        ArticleVo articleVo = this.articleService.queryArticleById(id);
        return Result.ok(articleVo);
    }

    /**
     * 新增数据
     *
     * @param articleDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<ArticleVo> insert(@RequestBody ArticleDto articleDto) {
        ArticleVo articleVo = this.articleService.saveArticleAndCategoryAndTag(articleDto);
        if (articleVo != null) {
            return Result.ok(articleVo, "操作成功");
        }
        return Result.fail("操作失败");
    }

    /**
     * 修改数据
     *
     * @param articleDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<ArticleVo> update(@RequestBody ArticleDto articleDto) {
        ArticleVo articleVo = this.articleService.updateArticleAndCategoryAndTag(articleDto);
        if (articleVo != null) {
            return Result.ok(articleVo, "操作成功");
        }
        return Result.fail("操作失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        Boolean bool = this.articleService.removeByArticle(idList);
        return Result.ok(bool);
    }
}

