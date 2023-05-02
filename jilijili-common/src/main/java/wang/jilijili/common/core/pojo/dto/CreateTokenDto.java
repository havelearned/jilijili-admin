package wang.jilijili.common.core.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Amani
 * @date 2023年02月13日 9:36
 */
@Schema(name = "Token创建条件")
@Data
public class CreateTokenDto {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 64, message = "用户名长度应该在4到64个字符之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 64, message = "密码长度应该在6到64个字符之间")
    private String password;
    /**
     * uid
     */
    private String checkKey;
    /**
     * 验证码
     */
    private String captcha;


}
