package wang.jilijili.music.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import wang.jilijili.music.pojo.dto.CommentsDto;
import wang.jilijili.music.pojo.entity.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import wang.jilijili.music.pojo.request.CommentsCreateRequest;
import wang.jilijili.music.pojo.vo.CommentsVo;

/**
* @author admin
* @description 针对表【comments】的数据库操作Service
* @createDate 2023-03-23 15:00:22
*/
public interface CommentsService extends IService<Comments> {

    /**
     * 创建评论
     * @author Amani
     * @date 23/3/2023 下午3:26
     * @param commentsCreateRequest 创建表单
     * @return wang.jilijili.music.pojo.dto.CommentsDto
     */
    CommentsDto create(CommentsCreateRequest commentsCreateRequest);

    IPage<CommentsVo> list(IPage<Comments> page, CommentsDto commentsDto);
}
