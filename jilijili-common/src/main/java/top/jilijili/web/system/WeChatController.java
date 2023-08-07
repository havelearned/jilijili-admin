package top.jilijili.web.system;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.annotation.JilJilOperationLog;
import top.jilijili.common.constant.SecurityConstant;
import top.jilijili.common.core.pojo.bo.UserConvertBo;
import top.jilijili.common.core.pojo.dto.Code2SessionDto;
import top.jilijili.common.core.pojo.vo.Result;
import top.jilijili.common.core.service.WeChatMpService;
import top.jilijili.common.enums.OperationType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序管理
 * @author Amani
 * @date 2023年02月21日 17:04
 */
@RestController
@RequestMapping("/weChat")
@Tag(name = "微信小程序管理")
public class WeChatController  {


    WxMaService wxMaService;
    WeChatMpService weChatMpService;
    UserConvertBo userConvertBo;

    PasswordEncoder passwordEncoder;

    public WeChatController(WxMaService wxMaService, WeChatMpService weChatMpService, UserConvertBo userConvertBo, PasswordEncoder passwordEncoder) {
        this.wxMaService = wxMaService;
        this.weChatMpService = weChatMpService;
        this.userConvertBo = userConvertBo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 获得重定向地址
     *
     * @param redirectUrl 重定向地址
     * @return null
     * @author Amani
     * @date 2023/2/25 10:04
     */
    @JilJilOperationLog(moduleName = "用户管理", type = OperationType.SELECT)
    @GetMapping("/auth_url")
    public String getAuthUrl(@RequestParam("redirectUrl") String redirectUrl) {
        return redirectUrl;
    }

    /**
     * 获取小程序openId等信息
     *
     * @param code2SessionDto code 和账号密码
     * @return 返回用户信息
     * @throws WxErrorException 微信错误信息
     */
    @JilJilOperationLog(moduleName = "用户管理", type = OperationType.SELECT)
    @PostMapping("/")
    public Result< Map<String, Object>> authUrl(@Validated @RequestBody Code2SessionDto code2SessionDto) throws WxErrorException {
        WxMaJscode2SessionResult sessionResult = wxMaService.jsCode2SessionInfo(code2SessionDto.getCode());
        code2SessionDto.setCode(sessionResult.getOpenid());
        String token = JWT.create()
                // 一般id,唯一字段
                .withSubject(code2SessionDto.getUsername())
                // 过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()));

        Map<String, Object> map = new HashMap<>(2);
        map.put("sessionResult", sessionResult);
        map.put("token", token);

        return Result.ok(map);
    }

}
