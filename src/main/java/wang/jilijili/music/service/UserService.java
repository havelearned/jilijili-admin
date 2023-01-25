package wang.jilijili.music.service;

import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.entity.User;

import java.util.List;

/**
 * @Auther: Amani
 * @Date: 2023/1/24 11:42
 * @Description:
 */
public interface UserService {

    /**
     * 返回所有用户信息
     */
    public abstract List<UserDto> userList();
}
