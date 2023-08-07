package top.jilijili.common.core.service;


import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.jilijili.common.core.pojo.vo.UserVo;
import top.jilijili.common.core.pojo.dto.UserCreateDto;
import top.jilijili.common.core.pojo.dto.UserDto;
import top.jilijili.common.core.pojo.dto.UserQueryDto;
import top.jilijili.common.core.pojo.entity.User;
import top.jilijili.common.core.pojo.request.UserUpdateRequest;
import top.jilijili.common.core.pojo.vo.Result;

import java.util.List;

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
     * @param id 用户id
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto get(String id);


    /**
     * 修改用户
     * @author Amani
     * @date 2023/3/5 11:44
     * @param userUpdateRequest 需要更新的字段
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto update(UserUpdateRequest userUpdateRequest);

    /**
     * 删除用户
     * @author Amani
     * @date 2023/3/5 11:44
     * @param id 用户id
     * @return wang.jilijili.music.pojo.vo.Result<?>
     */
    Result<?> delete(String id);

    /**
     * 创建用户
     * @author Amani
     * @date 2023/3/5 11:44
     * @param userCreateDto 创建用户dto
     * @param request 当前请求
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto create(UserCreateDto userCreateDto, HttpServletRequest request);

    /**
     * 获取在线所有用户
     * @author Amani
     * @date 2023/3/6 15:23
     * @param page 当前页
     * @param size 每页大小
     * @param userQueryDto 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<wang.jilijili.music.pojo.vo.UserVo>
     */
    IPage<UserVo> getOnlineUsers(Long page, Long size, UserQueryDto userQueryDto);

    /**
     * 搜索条件加分页
     * @author Amani
     * @date 2023/3/5 11:43
     * @param pageEntity 分页实体类
     * @param userQueryDto 查询条件
     * @return com.baomidou.mybatisplus.core.metadata.IPage<wang.jilijili.music.pojo.vo.UserVo>
     */
    IPage<UserVo> search(IPage<UserVo> pageEntity, UserQueryDto userQueryDto);


    /**
     * 获取当前用户
     * @author Amani
     * @date 2023/3/5 11:43
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto currentUser();

    /**
     * 通过userId获取菜单
     * @param userId
     * @return 菜单列表
     */
    List<Tree<String>> getMenu(String userId);
}
