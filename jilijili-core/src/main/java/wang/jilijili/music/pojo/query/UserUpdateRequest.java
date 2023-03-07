package wang.jilijili.music.pojo.query;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * @author admin
 */
@Data
public class UserUpdateRequest {

    @NotBlank(message = "id不能为空")
    private String id;
    @Size(min = 4, max = 64, message = "用户名长度应该在4到64个字符之间")
    private String username;
    private String nickname;
    private String gender;


}
