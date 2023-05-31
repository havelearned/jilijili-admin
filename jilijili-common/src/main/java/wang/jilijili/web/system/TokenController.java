package wang.jilijili.web.system;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.crypto.digest.DigestUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.common.constant.SecurityConstant;
import wang.jilijili.common.core.pojo.dto.CreateTokenDto;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.service.RedisService;
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
@Slf4j
public class TokenController {

    PasswordEncoder passwordEncoder;

    UserService userService;

    RedisService redisService;

    @Autowired
    public TokenController(PasswordEncoder passwordEncoder, UserService userService, RedisService redisService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.redisService = redisService;
    }

    /**
     * 后台生成图形验证码 ：有效
     *
     * @param key uid
     */
    @GetMapping(value = "/randomImage/{key}")
    public Result<String> randomImage(@PathVariable String key) {
        Result<String> res = new Result<>();
        try {
            LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50, 4, 1000);
            String code = lineCaptcha.getCode().toLowerCase();
            String realKey = DigestUtil.md5Hex(key + code);
            this.redisService.set(realKey, code, 3 * 60);
            String imageBase64Data = lineCaptcha.getImageBase64Data();
            res.setData(imageBase64Data);
            res.setCode(200);
            log.info("当前UID:{} ,生成验证码:{},redis key=>{}", key, code, realKey);
        } catch (Exception e) {
            res.setMessage("获取验证码出错:" + e.getMessage());
            e.printStackTrace();
        }
        return res;
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
        // 校验验证码
        String captcha = createTokenDto.getCaptcha().toLowerCase();
        String realKey = DigestUtil.md5Hex(createTokenDto.getCheckKey() + captcha);
        if (this.redisService.get(realKey) == null) {
            return Result.fail("验证码错误!!!");
        }
        log.info("登录UID:{},输入的验证码:{},redis key=>{}", createTokenDto.getCheckKey(), captcha, realKey);
        // 查询数据库
        UserDetails userDetails = this.userService.loadUserByUsername(createTokenDto.getUsername());
        String token = JWT.create()
                // 一般id,唯一字段
                .withSubject(userDetails.getUsername())
                // 过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()));

        return Result.ok(token);
    }

}
