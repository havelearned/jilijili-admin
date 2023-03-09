package wnag.jilijili.music.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Amani
 * @date 2023年03月09日 10:40
 */
@Data
public class MusicCreateRequest {
    @NotBlank(message = "歌曲名称不能为空")
    @Size(min = 4, max = 64, message = "音乐名称长度应该在4到64个字符之间")
    String name;

    @NotBlank
    Integer musicStatus;

}
