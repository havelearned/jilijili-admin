package top.jilijili.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.jilijili.blog.entity.Comment;
import top.jilijili.blog.entity.dto.CommentDto;
import top.jilijili.blog.entity.vo.CommentVo;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.CommentService;
import top.jilijili.common.control.SuperController;
import top.jilijili.common.entity.Result;
import top.jilijili.common.group.Query;

import java.io.Serializable;
import java.util.List;

/**
 * 文章评论管理
 *
 * @author makejava
 * @since 2023-08-14 14:19:28
 */
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class BlogCommentController extends SuperController {
    /**
     * 服务对象
     */

    private CommentService commentService;
    private ConvertMapper convertMapper;


    /**
     * 分页查询所有数据
     *
     * @param commentDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<CommentVo>> selectAll(@Validated(Query.class) CommentDto commentDto) {

        IPage<CommentVo> convert = this.commentService.pageList(commentDto);
        return Result.ok(convert);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result<CommentVo> selectOne(@PathVariable Serializable id) {
        return Result.ok(this.convertMapper.toCommentVo(this.commentService.getById(id)));
    }

    /**
     * 新增数据
     *
     * @param commentDto 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result<CommentVo> insert(@RequestBody CommentDto commentDto) {
        Comment comment = this.convertMapper.toComment(commentDto);
        boolean save = this.commentService.save(comment);
        if (save) {
            return Result.ok(this.convertMapper.toCommentVo(comment), "操作成功");
        }
        return Result.fail("操作失败");
    }

    /**
     * 修改数据
     *
     * @param commentDto 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result<CommentVo> update(@RequestBody CommentDto commentDto) {
        Comment comment = this.convertMapper.toComment(commentDto);
        boolean update = this.commentService.updateById(comment);
        if (update) {
            return Result.ok(this.convertMapper.toCommentVo(comment), "操作成功");
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
        return Result.ok(this.commentService.removeByIds(idList));
    }
}

