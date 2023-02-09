package wang.jilijili.music.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.music.pojo.convert.UserConvert;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.pojo.vo.UserVo;
import wang.jilijili.music.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: Amani
 * @Date: 2023/1/24 11:19
 * @Description:
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private UserService userService;
    private UserConvert userConvert;

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
    public UserVo create(@Validated @RequestBody UserCreateDto userCreateDto) {
        UserDto userDto = this.userService.create(userCreateDto);
        UserVo userVo = this.userConvert.toVo(userDto);
        return userVo;
    }


    @GetMapping("/list")
    public Page<UserVo> search(
            @PageableDefault(size = 5, sort = "createdTime", direction = Sort.Direction.DESC) Pageable pageable) {

        return userService.search(pageable).map(userConvert::toVo);
    }


    @GetMapping("/getallLoginUsers")
    public Result<?> getAllLoginUsers(){
        List<UserDto> dtoList =  userService.getAllLoginUsers();
        List<UserVo> result = dtoList.stream().map(userConvert::toVo).collect(Collectors.toList());

        return Result.ok(result);

    }

}
