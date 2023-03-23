package wang.jilijili.music.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.music.pojo.bo.CommentsBo;
import wang.jilijili.music.pojo.dto.CommentsDto;
import wang.jilijili.music.pojo.entity.Comments;
import wang.jilijili.music.pojo.request.CommentsCreateRequest;
import wang.jilijili.music.pojo.vo.CommentsVo;
import wang.jilijili.music.service.CommentsService;

import java.io.Serializable;
import java.util.List;

/**
 * (Comments)表控制层
 *
 * @author makejava
 * @since 2023-03-23 15:04:50
 */
@RestController
@RequestMapping("comments")
@Tag(name = "评论控制器")
public class CommentsController {
    /**
     * 服务对象
     */
    CommentsService commentsService;

    CommentsBo commentsBo;

    public CommentsController(CommentsService commentsService, CommentsBo commentsBo) {
        this.commentsService = commentsService;
        this.commentsBo = commentsBo;
    }

    /**
     * 分页查询所有数据
     *
     * @param commentsDto 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public Result<IPage<CommentsVo>> selectAll(CommentsDto commentsDto) {
        IPage<Comments> page = new Page<>(commentsDto.getPage(), commentsDto.getSize());
        IPage<CommentsVo> iPage = this.commentsService.list(page, commentsDto);
        return Result.ok(iPage);
    }

    /**
     * 通过主键查询单条数据
     * 查看详情
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.ok(this.commentsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param commentsCreateRequest 实体对象
     * @return 新增结果
     */
    @PostMapping("/")
    public Result<CommentsVo> insert(@RequestBody CommentsCreateRequest commentsCreateRequest) {
        CommentsDto commentsDto = this.commentsService.create(commentsCreateRequest);
        return Result.ok(this.commentsBo.toCommentVo(commentsDto));
    }

    /**
     * 修改数据
     *
     * @param comments 实体对象
     * @return 修改结果
     */
    @PutMapping("/")
    public Result<CommentsVo> update(@RequestBody Comments comments) {
        if (this.commentsService.updateById(comments)) {
            CommentsDto commentsDto = this.commentsBo.toCommentDto(comments);
            return Result.ok(this.commentsBo.toCommentVo(commentsDto));
        }
        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/")
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.commentsService.removeByIds(idList));
    }
}

