package wang.jilijili.common.core.pojo.dto;

import lombok.Data;
import wang.jilijili.common.enums.Gender;

import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @Date: 2023/1/24 11:26
 * @Description:
 */

@Data
public class UserDto {
    private String id;
    private String nickname;
    private String username;
    private List<RoleDto> roles;
    private Gender gender;
    private Integer locked;
    private Integer unseal;
    private String lastLoginIp;
    private String lastLoginTime;

    private Date createdTime;
}
