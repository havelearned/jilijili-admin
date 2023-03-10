package wang.jilijili.music.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.music.common.annotation.JilJilOperationLog;
import wang.jilijili.music.common.enums.OperationType;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.dto.CreateTokenDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.service.UserService;

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
    @JilJilOperationLog(moduleName = "用户管理", type = OperationType.ADD)
    @PostMapping("/")
    public Result<String> create(@RequestBody @Validated CreateTokenDto createTokenDto) {
        String token = super.createToken(createTokenDto, passwordEncoder);
        return Result.ok(token);
//        return Result.ok(this.userService.createToken(createTokenDto));
    }

}
