package top.jilijili.module.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import top.jilijili.common.enums.LoginOptionEnum;
import top.jilijili.common.group.Insert;
import top.jilijili.common.group.Login;
import top.jilijili.common.group.Update;


/**
 * @author Amani
 * @date 2023年06月22日 1:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysUserDto extends SuperDto {

    //-----------------------------------------LOGIN
    /**
     * 登录类型
     * QQ|WX|GITEE|GITHUB|DEFAULT
     */
    @NotBlank(message = "登录类型不能为空", groups = {Login.class})
    @Length(min = 6, max = 64, groups = {Login.class})
    private LoginOptionEnum loginType;

    /**
     * 时间戳
     */
    @NotNull(message = "时间戳不能为空", groups = {Login.class})
    private Long timestamp;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空", groups = {Login.class})
    @Length(min = 4, max = 6, groups = {Login.class})
    private String captcha;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {Login.class, Insert.class})
    @Length(min = 4, max = 64, groups = {Login.class, Insert.class})
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {Login.class, Insert.class})
    @Length(min = 6, max = 64, groups = {Login.class, Insert.class})
    private String password;
    //-----------------------------------------END


    @NotNull(message = "id不能为空", groups = {Update.class})
    private Long userId;

    private String nickname;
    private Integer userType;
    private String avatar;
    private Integer gender;
    private String email;
    private Integer enabled;
    private String phonenumber;


}
