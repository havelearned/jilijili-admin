package wang.jilijili.music.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Amani
 * @date 2023年03月09日 10:56
 */
@Data
public class MusicUpdateRequest {
    @NotBlank(message = "歌曲id不能为空")
    private String id;

    @NotBlank(message = "歌曲名称不能为空")
    @Size(min = 4, max = 64, message = "音乐名称长度应该在4到64个字符之间")
    private  String name;

    @NotBlank
    private String status;

    @NotBlank
    @Size(min = 0, max = 900, message = "歌曲信息长度应该在0到900个字符之间")
    private String description;
}
