package wang.jilijili.system.pojo.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;


    private List<Role> roles;
}
