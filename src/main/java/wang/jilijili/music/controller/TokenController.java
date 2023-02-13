package wang.jilijili.music.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.music.pojo.dto.CreateTokenDto;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.service.UserService;

/**
 * @author Amani
 * @date 2023年02月13日 9:34
 */
@RestController
@RequestMapping("/tokens")
@Tag(name = "token服务")
public class TokenController {
    UserService userService;

    public TokenController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/")
    public Result<?> create(@RequestBody @Validated CreateTokenDto createTokenDto) {
        return Result.ok(this.userService.createToken(createTokenDto));
    }

}
