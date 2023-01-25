package wang.jilijili.music.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.music.pojo.convert.UserConvert;
import wang.jilijili.music.pojo.vo.UserVo;
import wang.jilijili.music.service.UserService;

import java.util.List;

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

    @GetMapping("/")
    public List<UserVo> list() {

        return userService.userList().stream().map(userConvert::toVo).toList();
    }

}
