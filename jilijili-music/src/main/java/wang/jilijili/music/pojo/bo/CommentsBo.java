package wang.jilijili.music.pojo.bo;

import org.mapstruct.Mapper;
import wang.jilijili.music.pojo.dto.CommentsDto;
import wang.jilijili.music.pojo.entity.Comments;
import wang.jilijili.music.pojo.request.CommentsCreateRequest;
import wang.jilijili.music.pojo.vo.CommentsVo;

/**
 * @author Amani
 * @date 2023年03月23日 下午3:24
 */
@Mapper(componentModel = "spring")
public interface CommentsBo {


    /**
     * 转entity
     *
     * @param commentsCreateRequest 被转换的实体类
     * @return wang.jilijili.music.pojo.entity.Comments
     * @author Amani
     * @date 23/3/2023 下午3:29
     */
    Comments toComment(CommentsCreateRequest commentsCreateRequest);

    /**
     * 转dto
     *
     * @param comments entity
     * @return wang.jilijili.music.pojo.dto.CommentsDto
     * @author Amani
     * @date 23/3/2023 下午3:31
     */
    CommentsDto toCommentDto(Comments comments);

    /**
     * 转vo
     *
     * @param commentsDto dto
     * @return wang.jilijili.music.pojo.vo.CommentsVo
     * @author Amani
     * @date 23/3/2023 下午3:33
     */
    CommentsVo toCommentVo(CommentsDto commentsDto);

    /**
     * 转entity
     *
     * @param commentsDto dto
     * @return wang.jilijili.music.pojo.entity.Comments
     * @author Amani
     * @date 23/3/2023 下午3:54
     */
    Comments toComment(CommentsDto commentsDto);
}
