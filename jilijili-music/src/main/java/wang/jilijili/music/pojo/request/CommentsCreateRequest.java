package wang.jilijili.music.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Amani
 * @date 2023年03月23日 下午3:15
 */
@Data
public class CommentsCreateRequest {

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    @Size(min = 5,max = 255,message = "评论内容在5到255个字符之间")
    private String details;

    /**
     * 评论类型 1歌单2专辑3歌曲
     */
    @NotBlank(message = "评论类型不能为空")
    private Integer type;

    /**
     * 目标id，可能是歌单id、专辑id、歌曲id
     */
    @NotBlank(message = "目标id不能为空")
    private String targetid;
}
