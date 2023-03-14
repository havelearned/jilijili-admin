package wang.jilijili.system.pojo.vo;


import lombok.Data;
import wang.jilijili.common.enums.Gender;
import wang.jilijili.system.pojo.entity.Role;

import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @Date: 2023/1/24 11:21
 * @Description:
 */
@Data
public class UserVo {
    private String id;
    private String username;
    private String nickname;

    private Gender gender;

    private Integer unseal;

    private String lastLoginIp;

//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;


//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;


    private List<Role> roles;
}
