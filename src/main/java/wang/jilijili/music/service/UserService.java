package wang.jilijili.music.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import wang.jilijili.music.pojo.dto.CreateTokenDto;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.dto.UserQueryDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.pojo.vo.UserVo;

/**
 * @author admin
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2023-02-12 15:32:36
 */
public interface UserService extends IService<User>, UserDetailsService {

    UserDto get(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    Result<?> delete(String id);

    UserDto create(UserCreateDto userCreateDto, HttpServletRequest request);

    IPage<UserVo> getOnlineUsers(Long page, Long size, UserQueryDto userQueryDto);

    IPage<UserVo> search(IPage<User> pageEntity, UserQueryDto userQueryDto);

    String createToken(CreateTokenDto createTokenDto);

    UserDto currentUser();
}
