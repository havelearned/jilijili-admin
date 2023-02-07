package wang.jilijili.music.pojo.dto;

import lombok.Data;
import wang.jilijili.music.common.enums.Gender;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Amani
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
    private Integer enabled;
    private String lastLoginIp;

    private Date createdTime;
}
