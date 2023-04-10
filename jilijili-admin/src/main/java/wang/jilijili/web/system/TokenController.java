package wang.jilijili.web.system;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.jilijili.common.constant.SecurityConstant;
import wang.jilijili.common.core.pojo.dto.CreateTokenDto;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.service.UserService;

import java.util.Date;


/**
 * 令牌管理
 *
 * @author Amani
 * @date 2023年02月13日 9:34
 */
@RestController
@RequestMapping("/tokens")
@Tag(name = "token服务")
public class TokenController {

    PasswordEncoder passwordEncoder;

    UserService userService;

    public TokenController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
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
        UserDetails userDetails = this.userService.loadUserByUsername(createTokenDto.getUsername());


        String token = JWT.create()
                .withSubject(userDetails.getUsername()) // 一般id,唯一字段
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME)) // 过期时间
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()));

        return Result.ok(token);
//        return Result.ok(this.userService.createToken(createTokenDto));
    }

}
