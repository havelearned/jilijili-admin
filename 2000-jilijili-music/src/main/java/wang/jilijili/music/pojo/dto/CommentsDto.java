package wang.jilijili.music.pojo.dto;

import lombok.Data;
import wang.jilijili.common.core.pojo.dto.QueryDto;

/**
 * @author Amani
 * @date 2023年03月23日 下午3:15
 */
@Data
public class CommentsDto extends QueryDto {

    private String id;

    /**
     * 评论内容
     */
    private String details;

    /**
     * 评论类型 1歌单2专辑3歌曲
     */
    private Integer type;

    /**
     * 目标id，可能是歌单id、专辑id、歌曲id
     */
    private String targetid;

}
