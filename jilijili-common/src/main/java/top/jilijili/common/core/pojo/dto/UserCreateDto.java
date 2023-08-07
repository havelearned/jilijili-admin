package top.jilijili.common.core.pojo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


/**
 * @author admin
 * @Date: 2023/1/28 10:29
 * @Description: <>创建用户</> 数据传输对象
 */

@Data
public class UserCreateDto {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 64, message = "用户名长度应该在4到64个字符之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 64, message = "密码长度应该在6到64个字符之间")
    private String password;

    private String nickname;

    private String gender;

    private Integer unseal;


}
