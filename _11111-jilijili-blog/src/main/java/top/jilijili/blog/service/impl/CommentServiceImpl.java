package top.jilijili.blog.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.jilijili.blog.entity.Comment;
import top.jilijili.blog.entity.dto.CommentDto;
import top.jilijili.blog.entity.vo.CommentVo;
import top.jilijili.blog.mapper.CommentMapper;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.CommentService;

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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    private ConvertMapper convertMapper;
    private CommentMapper commentMapper;

    @Override
    public IPage<CommentVo> pageList(CommentDto commentDto) {
        IPage<CommentVo> convert = new Page<>(commentDto.getPage(), commentDto.getSize());

        List<CommentVo> commentVoList = this.commentMapper.queryCommentAntUserByCommentId(commentDto.getArticleId(), commentDto.getPage(), commentDto.getSize());

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
        return list.stream().filter(item -> Objects.equals(item.getChildId(), rootFlag)).collect(Collectors.toList());
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




