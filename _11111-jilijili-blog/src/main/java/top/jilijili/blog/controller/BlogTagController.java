package top.jilijili.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.jilijili.blog.entity.Tag;
import top.jilijili.blog.entity.dto.TagDto;
import top.jilijili.blog.entity.vo.TagVo;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.TagService;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;

import java.io.Serializable;
import java.util.List;

/**
 * 博客标签(BlogTag)表控制层
 *
 * @author makejava
 * @since 2023-08-14 13:55:51
 */
@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class BlogTagController extends SuperController {
    /**
     * 服务对象
     */
    private TagService tagService;
    private ConvertMapper convertMapper;


    /**
     * 分页查询所有数据
     *
     * @param tagDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<TagVo>> selectAll(TagDto tagDto) {
        IPage<Tag> page = new Page<>(tagDto.getPage(), tagDto.getSize());
        IPage<TagVo> convert = this.tagService.lambdaQuery()
                .select(Tag::getTagId, Tag::getTagTitle, Tag::getChildId, Tag::getUpdatedTime, Tag::getCreatedTime)
                .like(StringUtils.hasText(tagDto.getTagTitle()), Tag::getTagTitle, tagDto.getKeyword())
                .eq(tagDto.getTagId() != null, Tag::getTagId, tagDto.getTagId())
                .between(tagDto.getCreatedTime() != null, Tag::getCreatedTime, tagDto.getCreatedTime(), tagDto.getComparisonTime())
                .page(page).convert(this.convertMapper::toTagVo);
        return Result.ok(convert);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<TagVo> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.convertMapper.toTagVo(this.tagService.getById(id)));
    }

    /**
     * 新增数据
     *
     * @param tagDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<TagVo> insert(@RequestBody TagDto tagDto) {
        Tag tag = this.convertMapper.toTag(tagDto);
        boolean save = this.tagService.save(tag);
        if (save) {
            return Result.ok(this.convertMapper.toTagVo(tag), "操作成功");
        }
        return Result.fail("失败");
    }

    /**
     * 修改数据
     *
     * @param tagDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<TagVo> update(@RequestBody TagDto tagDto) {
        Tag tag = this.convertMapper.toTag(tagDto);
        boolean update = this.tagService.updateById(tag);
        if (update) {
            return Result.ok(this.convertMapper.toTagVo(tag), "操作成功");
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
        return Result.ok(this.tagService.removeBatchByIds(idList));
    }
}

