package wang.jilijili.music.controller;

import org.springframework.web.bind.annotation.*;
import wang.jilijili.music.pojo.convert.UserConvert;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
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
public class UserController {

  private UserService userService;
  private UserConvert userConvert;

  public UserController(UserService userService, UserConvert userConvert) {
    this.userService = userService;
    this.userConvert = userConvert;
  }

  @PostMapping("/")
  public UserVo create(@RequestBody UserCreateDto userCreateDto) {
    UserDto userDto = this.userService.create(userCreateDto);
    UserVo userVo = this.userConvert.toVo(userDto);
    return userVo;
  }


  @GetMapping("/")
  public List<UserVo> list() {

    return userService.userList().stream().map(userConvert::toVo).collect(Collectors.toList());
  }

}
