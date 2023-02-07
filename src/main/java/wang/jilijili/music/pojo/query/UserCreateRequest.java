package wang.jilijili.music.pojo.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserCreateRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4,max = 64,message = "用户名长度应该在4到64个字符之间")
    private String username;

    private String password;
}
