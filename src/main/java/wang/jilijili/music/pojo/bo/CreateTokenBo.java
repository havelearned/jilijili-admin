package wang.jilijili.music.pojo.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Amani
 * @date 2023年03月01日 11:17
 */
@Data
public class CreateTokenBo {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4,max = 64,message = "用户名长度应该在4到64个字符之间")
    protected String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 64,message = "密码长度应该在6到64个字符之间")
    protected String password;
}
