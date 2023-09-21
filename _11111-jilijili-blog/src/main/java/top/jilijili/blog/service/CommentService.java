package top.jilijili.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.Comment;
import top.jilijili.module.entity.dto.CommentDto;
import top.jilijili.module.entity.vo.CommentVo;

/**
* @author admin
* @description 针对表【blog_comment(文章评论表)】的数据库操作Service
* @createDate 2023-08-13 23:19:57
*/
public interface CommentService extends IService<Comment> {

    IPage<CommentVo> pageList(CommentDto commentDto);

    /**
     * 添加评论信息
     * @param commentDto
     * @return
     */
    CommentVo saveComment(CommentDto commentDto);

    /**
     * 查询所有评论内容
     * @param commentDto
     * @return 树形结构
     */
    IPage<CommentVo> queryCommentAll(CommentDto commentDto);
}
