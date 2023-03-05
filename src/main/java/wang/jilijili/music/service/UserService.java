package wang.jilijili.music.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    /**
     * 通过id查询用户
     * @author Amani
     * @date 2023/3/5 11:45
     * @param id
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto get(String id);


    /**
     * 修改用户
     * @author Amani
     * @date 2023/3/5 11:44
     * @param userUpdateRequest
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto update(UserUpdateRequest userUpdateRequest);

    /**
     * 删除用户
     * @author Amani
     * @date 2023/3/5 11:44
     * @param id
     * @return wang.jilijili.music.pojo.vo.Result<?>
     */
    Result<?> delete(String id);

    /**
     * 创建用户
     * @author Amani
     * @date 2023/3/5 11:44
     * @param userCreateDto
     * @param request
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto create(UserCreateDto userCreateDto, HttpServletRequest request);

    /**
     * 获取在线所有用户
     * @author Amani
     * @date 2023/3/5 11:43
     * @param null
     * @return null
     */
    IPage<UserVo> getOnlineUsers(Long page, Long size, UserQueryDto userQueryDto);

    /**
     * 搜索条件加分页
     * @author Amani
     * @date 2023/3/5 11:43
     * @param pageEntity
     * @param userQueryDto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<wang.jilijili.music.pojo.vo.UserVo>
     */
    IPage<UserVo> search(IPage<User> pageEntity, UserQueryDto userQueryDto);


    /**
     * 获取当前用户
     * @author Amani
     * @date 2023/3/5 11:43
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto currentUser();
}
