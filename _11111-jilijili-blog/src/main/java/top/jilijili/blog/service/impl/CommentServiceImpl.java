package top.jilijili.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import top.jilijili.blog.mapper.CommentMapper;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.ArticleService;
import top.jilijili.blog.service.CommentService;
import top.jilijili.common.heandler.JiliException;
import top.jilijili.module.pojo.entity.blog.Article;
import top.jilijili.module.pojo.entity.blog.Comment;
import top.jilijili.module.pojo.dto.blog.CommentDto;
import top.jilijili.module.pojo.vo.blog.CommentVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【blog_comment(文章评论表)】的数据库操作Service实现
 * @createDate 2023-08-13 23:19:57
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    private ConvertMapper convertMapper;
    private CommentMapper commentMapper;
    private ArticleService articleService;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public CommentVo saveComment(CommentDto commentDto) {
        Comment comment = this.convertMapper.toComment(commentDto);
        boolean save = this.save(comment);
        Article article = this.articleService.getById(commentDto.getArticleId());
        if (save) {
            try {
                this.articleService.lambdaUpdate().set(Article::getCommentCount, article.getCommentCount() + 1).eq(Article::getArticleId, article.getArticleId());
            } catch (Exception e) {
                throw new JiliException("评论数量更新失败");
            }
        }
        return this.convertMapper.toCommentVo(comment);
    }

    @Override
    public IPage<CommentVo> queryCommentAll(CommentDto commentDto) {
        IPage<Comment> commentVoPage = new Page<>(commentDto.getPage(), commentDto.getSize());
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(commentDto.getContent()), "c.content", commentDto.getContent())
                .eq(commentDto.getCommentStatus() != null, "c.comment_status", commentDto.getCommentStatus())
                .orderByDesc("created_time");
        return this.commentMapper.queryCommentAntUserAll(commentVoPage, queryWrapper);
    }

    @Override
    public IPage<CommentVo> pageList(CommentDto commentDto) {
        IPage<CommentVo> convert = new Page<>(commentDto.getPage(), commentDto.getSize());
        List<CommentVo> commentVoList = this.commentMapper
                .queryCommentAntUserByCommentId(commentDto.getArticleId(), commentDto.getPage(), commentDto.getSize());
        // 得到最顶级评论
        List<CommentVo> root = getRootList(commentVoList, "0");

        for (CommentVo commentVo : root) {
            commentVo.setChildList(getChildList(commentVo.getCommentId(), commentVoList));
        }

        convert.setRecords(root);
        convert.setTotal(commentVoList.size());
        return convert;


    }


    /**
     * 递归获取评论
     *
     * @param list
     * @param rootFlag
     * @return
     */
    public List<CommentVo> getRootList(List<CommentVo> list, String rootFlag) {
        return list.stream().filter(item -> item != null && (item.getChildId().equals(rootFlag)))
                .collect(Collectors.toList());
    }

    public List<CommentVo> getChildList(String commentId, List<CommentVo> data) {
        List<CommentVo> childList = new ArrayList<>();

        for (CommentVo commentVo : data) {
            if (Objects.equals(commentVo.getChildId(), commentId)) {
                childList.add(commentVo);
            }
        }

        for (CommentVo commentVo : childList) {
            commentVo.setChildList(getChildList(commentVo.getCommentId(), data));
        }

        if (childList.isEmpty()) {
            return null;
        }
        return childList;
    }
}




