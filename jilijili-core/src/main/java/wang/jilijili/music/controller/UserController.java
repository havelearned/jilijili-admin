package wang.jilijili.music.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.music.common.annotation.JilJilOperationLog;
import wang.jilijili.music.common.enums.OperationType;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.bo.UserConvertBo;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.dto.UserQueryDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.request.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.pojo.vo.UserVo;
import wang.jilijili.music.service.UserService;

import static wang.jilijili.music.common.constant.ModuleNameConstant.USER_MANAGEMENT;
import static wang.jilijili.music.common.constant.RoleConstant.ROLE_SUPER_ADMIN;

/**
 * @author admin
 * @Date: 2023/1/24 11:19
 * @Description:
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Tag(name = "用户管理")
public class UserController extends BaseController<User, UserMapper, UserService> {

    private final UserService userService;
    private final UserConvertBo userConvertBo;

    public UserController(UserMapper mapper, UserService service, UserService userService, UserConvertBo userConvertBo) {
        super(mapper, service);
        this.userService = userService;
        this.userConvertBo = userConvertBo;
    }

    /**
     * 通过用户名查询
     * @author Amani
     * @date 2023/3/10 11:56
     * @param username  username
     * @return wang.jilijili.music.pojo.vo.Result<?>
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.SELECT)
    @GetMapping("/checkUsername/{username}")
    public Result<?> checkUsername(@PathVariable("username") String username) {
        long count = this.service.count(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return count <= 0 ? Result.ok() : Result.fail();

    }

    /**
     * 分页查询
     * @param userQueryDto 搜索条件
     * @return wang.jilijili.music.pojo.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage < wang.jilijili.music.pojo.vo.UserVo>>
     * @author Amani
     * @date 2023/3/5 11:45
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.SELECT)
    @GetMapping("/list")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    public Result<IPage<UserVo>> search(UserQueryDto userQueryDto) {
        IPage<User> pageEntity = new Page<>(userQueryDto.getPage(), userQueryDto.getSize());
        IPage<UserVo> voIpage = this.userService.search(pageEntity, userQueryDto);
        return Result.ok(voIpage);
    }

    /**
     *  查询用户
     * @author Amani
     * @date 2023/3/10 11:55
     * @param id id
     * @return wang.jilijili.music.pojo.vo.Result<wang.jilijili.music.pojo.vo.UserVo>
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.SELECT)
    @GetMapping("/{id}")
    public Result<UserVo> get(@PathVariable("id") String id) {
        UserVo userVo = this.userConvertBo.toVo(this.userService.get(id));
        return Result.ok(userVo);

    }

    /**
     * 修改用户
     *
     * @param userUpdateRequest 修改表单
     * @return wang.jilijili.music.pojo.vo.Result<wang.jilijili.music.pojo.vo.UserVo>
     * @author Amani
     * @date 2023/3/5 11:46
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.UPDATE)
    @PutMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    public Result<UserVo> update(@Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        UserVo userVo = this.userConvertBo.toVo(this.userService.update(userUpdateRequest));
        return Result.ok(userVo);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return wang.jilijili.music.pojo.vo.Result<?>
     * @author Amani
     * @date 2023/3/5 11:46
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.DELETED)
    @DeleteMapping("/{id}")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    public Result<?> delete(@PathVariable("id") String id) {
        return this.userService.delete(id);
    }

    /**
     * 创建用户
     *
     * @param userCreateDto dto
     * @param request       当前请求
     * @return wang.jilijili.music.pojo.vo.Result<wang.jilijili.music.pojo.vo.UserVo>
     * @author Amani
     * @date 2023/3/5 11:47
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.ADD)
    @PostMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN})
    public Result<UserVo> create(@Validated @RequestBody UserCreateDto userCreateDto,
                                 HttpServletRequest request) {
        UserDto userDto = this.userService.create(userCreateDto, request);
        UserVo userVo = this.userConvertBo.toVo(userDto);
        return Result.ok(userVo);
    }

    /**
     * 查询在线用户
     * @author Amani
     * @date 2023/3/10 11:56
     * @param userQueryDto  查询条件
     * @return wang.jilijili.music.pojo.vo.Result<com.baomidou.mybatisplus.core.metadata.IPage<wang.jilijili.music.pojo.vo.UserVo>>
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.SELECT)
    @GetMapping("/getOnlineUsers")
    public Result<IPage<UserVo>> getOnlineUsers(
            @RequestBody UserQueryDto userQueryDto) {
        IPage<UserVo> result = userService.getOnlineUsers(userQueryDto.getPage(), userQueryDto.getSize(), userQueryDto);
        return Result.ok(result);
    }

    /**
     * 查询当前用户
     * @author Amani
     * @date 2023/3/10 11:57
     * @return wang.jilijili.music.pojo.vo.Result<wang.jilijili.music.pojo.vo.UserVo>
     */
    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.SELECT)
    @GetMapping("/me")
    public Result<UserVo> me() {
        UserDto userDto = this.userService.currentUser();
        return Result.ok(userConvertBo.toVo(userDto));
    }

    /**
     * 导出
     * @author Amani
     * @date 2023/3/10 11:57
     * @param userQueryDto 导出条件
     * @param response 响应
     */

    @JilJilOperationLog(moduleName = USER_MANAGEMENT, type = OperationType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody UserQueryDto userQueryDto,
                       HttpServletResponse response) {

        super.export(userQueryDto, response);
    }


}
