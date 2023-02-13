package wang.jilijili.music.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.music.pojo.convert.UserConvert;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.dto.UserQueryDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.pojo.vo.UserVo;
import wang.jilijili.music.service.UserService;

/**
 * @Auther: Amani
 * @Date: 2023/1/24 11:19
 * @Description:
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
@Tag(name = "用户管理")
public class UserController {

    private final UserService userService;
    private final UserConvert userConvert;

    public UserController(UserService userService, UserConvert userConvert) {
        this.userService = userService;
        this.userConvert = userConvert;
    }

    @GetMapping("/{id}")
    public Result<UserVo> get(@PathVariable("id") String id) {
        UserVo userVo = this.userConvert.toVo(this.userService.get(id));
        return Result.ok(userVo);

    }

    @PutMapping("/{id}")
    public Result<UserVo> update(
            @PathVariable("id") String id,
            @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        UserVo userVo = this.userConvert.toVo(this.userService.update(id, userUpdateRequest));
        return Result.ok(userVo);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable("id") String id) {
        Result<?> result = this.userService.delete(id);
        return result;
    }

    @PostMapping("/")
    public Result<UserVo> create(@Validated @RequestBody UserCreateDto userCreateDto,
                                 HttpServletRequest request) {
        UserDto userDto = this.userService.create(userCreateDto, request);
        UserVo userVo = this.userConvert.toVo(userDto);
        return Result.ok(userVo);
    }


    @GetMapping("/list/{page}/{size}")
    public Result<IPage<UserVo>> search(@PathVariable Long page,
                                        @PathVariable Long size,
                                        @RequestBody UserQueryDto userQueryDto) {
        IPage<User> pageEntity = new Page<>(page, size);
        IPage<UserVo> voIPage = this.userService.search(pageEntity, userQueryDto);
        return Result.ok(voIPage);
    }


    @GetMapping("/getOnlineUsers/{page}/{size}")
    public Result<IPage<UserVo>> getOnlineUsers(
            @PathVariable Long page,
            @PathVariable Long size,
            @RequestBody UserQueryDto userQueryDto) {
        IPage<UserVo> result = userService.getOnlineUsers(page, size, userQueryDto);
        return Result.ok(result);
    }

    @GetMapping("/me")
    public Result<UserVo> me() {
        UserDto userDto = this.userService.currentUser();
        return Result.ok(userConvert.toVo(userDto));
    }

}
