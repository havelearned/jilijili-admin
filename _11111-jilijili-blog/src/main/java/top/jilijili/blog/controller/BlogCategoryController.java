package top.jilijili.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.jilijili.blog.entity.Category;
import top.jilijili.blog.entity.dto.CategoryDto;
import top.jilijili.blog.entity.vo.CategoryVo;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.CategoryService;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;

import java.io.Serializable;
import java.util.List;

/**
 * 文章分类(CategoryDto)表控制层
 *
 * @author makejava
 * @since 2023-08-14 14:07:28
 */
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class BlogCategoryController extends SuperController {
    /**
     * 服务对象
     */
    private CategoryService categoryService;
    private ConvertMapper convertMapper;

    /**
     * 分页查询所有数据
     *
     * @param categoryDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<CategoryVo>> selectAll(CategoryDto categoryDto) {
        IPage<Category> page = new Page<>(categoryDto.getPage(), categoryDto.getSize());
        IPage<CategoryVo> convert = this.categoryService.lambdaQuery()
                .like(StringUtils.hasText(categoryDto.getKeyword()),Category::getTitle,categoryDto.getTitle())
                .orderByDesc(Category::getCreatedTime)
                .page(page)
                .convert(this.convertMapper::toCategoryVo);
        return Result.ok(convert);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<CategoryVo> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.convertMapper.toCategoryVo(this.categoryService.getById(id)));
    }

    /**
     * 新增数据
     *
     * @param categoryDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<CategoryVo> insert(@RequestBody CategoryDto categoryDto) {
        Category category = this.convertMapper.toCategory(categoryDto);
        boolean save = this.categoryService.save(category);
        if (save) {
            return Result.ok(this.convertMapper.toCategoryVo(category), "操作成功");
        }
        return Result.fail("失败");
    }

    /**
     * 修改数据
     *
     * @param categoryDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<CategoryVo> update(@RequestBody CategoryDto categoryDto) {
        Category category = this.convertMapper.toCategory(categoryDto);
        boolean update = this.categoryService.updateById(category);
        if (update) {
            return Result.ok(this.convertMapper.toCategoryVo(category), "操作成功");
        }
        return Result.fail("失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result<Boolean> delete(@RequestBody List<Long> idList) {
        return Result.ok(this.categoryService.removeBatchByIds(idList));
    }
}

