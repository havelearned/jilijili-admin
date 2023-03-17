package wang.jilijili.web.system;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.common.enums.OperationType;
import wang.jilijili.framework.annotation.JilJilOperationLog;
import wang.jilijili.system.mapper.UserMapper;
import wang.jilijili.system.pojo.dto.CreateTokenDto;
import wang.jilijili.system.pojo.entity.User;
import wang.jilijili.system.pojo.vo.Result;
import wang.jilijili.system.service.UserService;
import wang.jilijili.web.common.BaseController;

/**
 * @author Amani
 * @date 2023年02月13日 9:34
 */
@RestController
@RequestMapping("/tokens")
@Tag(name = "token服务")
public class TokenController extends BaseController<User, UserMapper, UserService> {

    PasswordEncoder passwordEncoder;

    public TokenController(UserMapper mapper, UserService service, PasswordEncoder passwordEncoder) {
        super(mapper, service);
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
