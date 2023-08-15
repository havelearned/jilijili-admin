package top.jilijili.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.jilijili.blog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.blog.entity.dto.CommentDto;
import top.jilijili.blog.entity.vo.CommentVo;

/**
* @author admin
* @description 针对表【blog_comment(文章评论表)】的数据库操作Service
* @createDate 2023-08-13 23:19:57
*/
public interface CommentService extends IService<Comment> {

    IPage<CommentVo> pageList(CommentDto commentDto);
}
