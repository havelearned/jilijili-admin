package wang.jilijili.music.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import wang.jilijili.common.core.pojo.dto.QueryDto;

import java.util.Date;

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


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
}
