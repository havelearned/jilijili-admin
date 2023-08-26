package top.jilijili.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.jilijili.blog.entity.Article;
import top.jilijili.blog.entity.dto.ArticleDto;
import top.jilijili.blog.entity.vo.ArticleVo;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.ArticleService;
import top.jilijili.blog.service.TagCategoryArticleService;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 博客文章管理
 *
 * @author makejava
 * @since 2023-08-13 23:22:12
 */
@RestController
@RequestMapping("/blogArticle")
@AllArgsConstructor
public class BlogArticleController extends SuperController {
    /**
     * 服务对象
     */
    private ArticleService articleService;
    private ConvertMapper convertMapper;
    private TagCategoryArticleService tagCategoryArticleService;

    /**
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
        Article byId = this.articleService.getById(id);
        ArticleVo articleVo = this.convertMapper.toArticleVo(byId);
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
    public Result<Boolean> delete(@RequestParam("idList") List<Long> idList) {
        Boolean bool = this.articleService.removeByArticle(idList);
        return Result.ok(bool);
    }
}

