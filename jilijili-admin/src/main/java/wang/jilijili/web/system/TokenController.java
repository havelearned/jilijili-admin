package wang.jilijili.web.system;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.common.core.controller.BaseController;
import wang.jilijili.common.core.mapper.UserMapper;
import wang.jilijili.common.core.pojo.dto.CreateTokenDto;
import wang.jilijili.common.core.pojo.vo.Result;



/**
 * 令牌控制器
 *
 * @author Amani
 * @date 2023年02月13日 9:34
 */
@RestController
@RequestMapping("/tokens")
@Tag(name = "token服务")
public class TokenController extends BaseController<UserMapper> {

    PasswordEncoder passwordEncoder;

    public TokenController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 创建令牌
     *
     * @param createTokenDto 令牌类型
     * @return wang.jilijili.music.pojo.vo.Result<java.lang.String>
     * @author Amani
     * @date 2023/3/10 11:59
     */
    @PostMapping("/")
    public Result<String> create(@RequestBody @Validated CreateTokenDto createTokenDto) {
        String token = super.createToken(createTokenDto, passwordEncoder);
        return Result.ok(token);
//        return Result.ok(this.userService.createToken(createTokenDto));
    }

}
