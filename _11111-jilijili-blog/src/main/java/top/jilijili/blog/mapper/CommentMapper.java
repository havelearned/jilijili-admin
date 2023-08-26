package top.jilijili.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.blog.entity.Comment;
import top.jilijili.blog.entity.vo.CommentVo;

import java.util.List;

/**
 * @author admin
 * @description 针对表【blog_comment(文章评论表)】的数据库操作Mapper
 * @createDate 2023-08-13 23:19:57
 * @Entity top.jilijili.blog.entity.Comment
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {



    List<CommentVo> queryCommentAntUserByCommentId(Long articleId, Integer page, Integer size);
}




