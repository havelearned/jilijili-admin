package wang.jilijili.music.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.bo.UserConvertBo;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.dto.UserQueryDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.pojo.vo.UserVo;
import wang.jilijili.music.service.UserService;

import static wang.jilijili.music.common.enums.RoleConstant.ROLE_SUPER_ADMIN;

/**
 * @Auther: Amani
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

    @GetMapping("/list")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN}) // 有前缀
//    @PreAuthorize(value = "hasAnyRole('ROLE_SUPER_ADMIN')") // 没有前缀
    public Result<IPage<UserVo>> search(UserQueryDto userQueryDto) {
        IPage<User> pageEntity = new Page<>(userQueryDto.getPage(), userQueryDto.getSize());
        IPage<UserVo> voIPage = this.userService.search(pageEntity, userQueryDto);
        return Result.ok(voIPage);
    }

    @GetMapping("/{id}")
    public Result<UserVo> get(@PathVariable("id") String id) {
        UserVo userVo = this.userConvertBo.toVo(this.userService.get(id));
        return Result.ok(userVo);

    }

    @PutMapping("/{id}")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN}) // 有前缀
    public Result<UserVo> update(
            @PathVariable("id") String id,
            @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        UserVo userVo = this.userConvertBo.toVo(this.userService.update(id, userUpdateRequest));
        return Result.ok(userVo);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN}) // 有前缀
    public Result<?> delete(@PathVariable("id") String id) {
        return this.userService.delete(id);
    }

    @PostMapping("/")
    @RolesAllowed(value = {ROLE_SUPER_ADMIN}) // 有前缀
    public Result<UserVo> create(@Validated @RequestBody UserCreateDto userCreateDto,
                                 HttpServletRequest request) {
        UserDto userDto = this.userService.create(userCreateDto, request);
        UserVo userVo = this.userConvertBo.toVo(userDto);
        return Result.ok(userVo);
    }

    @GetMapping("/getOnlineUsers")
    public Result<IPage<UserVo>> getOnlineUsers(
            @RequestBody UserQueryDto userQueryDto) {
        IPage<UserVo> result = userService.getOnlineUsers(userQueryDto.getPage(), userQueryDto.getSize(), userQueryDto);
        return Result.ok(result);
    }

    @GetMapping("/me")
    public Result<UserVo> me() {
        UserDto userDto = this.userService.currentUser();
        return Result.ok(userConvertBo.toVo(userDto));
    }

    @PostMapping("/export")
    public void export(@RequestBody UserQueryDto userQueryDto,
                       HttpServletResponse response) {

        super.export(userQueryDto, response);
    }


}
