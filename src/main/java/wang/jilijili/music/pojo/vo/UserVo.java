package wang.jilijili.music.pojo.vo;

import lombok.Data;
import wang.jilijili.music.common.enums.Gender;
import wang.jilijili.music.pojo.entity.Role;

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
    private List<Role> roles;

}
