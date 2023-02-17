package wang.jilijili.music.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import wang.jilijili.music.common.enums.Gender;
import wang.jilijili.music.pojo.entity.Role;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Amani
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

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;


    private List<Role> roles;
}
