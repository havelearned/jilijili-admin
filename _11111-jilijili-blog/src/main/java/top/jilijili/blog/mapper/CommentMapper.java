package top.jilijili.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.entity.Comment;
import top.jilijili.module.entity.vo.CommentVo;

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


    IPage<CommentVo> queryCommentAntUserAll(@Param("page") IPage<Comment> page,
                                            @Param(Constants.WRAPPER) QueryWrapper<Comment> ew);
}




