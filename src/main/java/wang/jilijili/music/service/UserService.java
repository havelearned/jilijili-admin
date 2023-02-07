package wang.jilijili.music.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;

import java.util.Collection;
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
     * 创建用户
     */
    public abstract UserDto create(UserCreateDto userCreateDto);

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    Result<?> delete(String id);

    Page<UserDto> search(Pageable pageable);
}
