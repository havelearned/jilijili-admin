package wang.jilijili.music.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;

import java.util.List;

/**
 * @Auther: Amani
 * @Date: 2023/1/24 11:42
 * @Description:
 */
public interface UserService extends UserDetailsService {

    /**
     * 返回所有用户信息
     */
    public abstract List<UserDto> userList();

    /**
     *  创建用户
     * */
    public abstract UserDto create(UserCreateDto userCreateDto);
}
