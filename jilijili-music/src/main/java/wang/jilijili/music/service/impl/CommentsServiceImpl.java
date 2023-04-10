package wang.jilijili.music.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.music.mapper.CommentsMapper;
import wang.jilijili.music.pojo.bo.CommentsBo;
import wang.jilijili.music.pojo.dto.CommentsDto;
import wang.jilijili.music.pojo.entity.Comments;
import wang.jilijili.music.pojo.request.CommentsCreateRequest;
import wang.jilijili.music.pojo.vo.CommentsVo;
import wang.jilijili.music.service.CommentsService;

/**
 * @author admin
 * @description 针对表【comments】的数据库操作Service实现
 * @createDate 2023-03-23 15:00:22
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
        implements CommentsService {

    CommentsMapper commentsMapper;

    CommentsBo commentsBo;

    public CommentsServiceImpl(CommentsMapper commentsMapper, CommentsBo commentsBo) {
        this.commentsMapper = commentsMapper;
        this.commentsBo = commentsBo;
    }

    @Override
    @DS("slave_1")
    
    public CommentsDto create(CommentsCreateRequest commentsCreateRequest) {
        Comments comments = commentsBo.toComment(commentsCreateRequest);
        comments.setId(KsuidGenerator.generate());
        boolean save = this.save(comments);
        if(save){
            return this.commentsBo.toCommentDto(comments);
        }
        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    @Override
    public IPage<CommentsVo> list(IPage<Comments> page, CommentsDto commentsDto) {
         Comments comments =  this.commentsBo.toComment(commentsDto);

        return null;
    }


}




