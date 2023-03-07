package wang.jilijili.music.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.music.common.enums.JilJilOperationLog;
import wang.jilijili.music.common.enums.OperationType;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.bo.UserConvertBo;
import wang.jilijili.music.pojo.dto.Code2SessionDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.service.UserService;
import wang.jilijili.music.service.WeChatMpService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amani
 * @date 2023年02月21日 17:04
 */
@RestController
@RequestMapping("/weChat")
@Tag(name = "微信小程序控制器")
public class WeChatController extends BaseController<User, UserMapper, UserService> {


    WxMaService wxMaService;
    WeChatMpService weChatMpService;
    UserConvertBo userConvertBo;

    PasswordEncoder passwordEncoder;

    public WeChatController(UserMapper mapper, UserService service,
                            WxMaService wxMaService, WeChatMpService weChatMpService,
                            PasswordEncoder passwordEncoder, UserConvertBo userConvertBo) {
        super(mapper, service);
        this.wxMaService = wxMaService;
        this.weChatMpService = weChatMpService;
        this.userConvertBo = userConvertBo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param redirectUrl 重定向地址
     * @return null
     * @author Amani
     * @date 2023/2/25 10:04
     */
    @JilJilOperationLog(moduleName = "用户管理", type = OperationType.SELECT)
    @GetMapping("/auth_url")
    public String getAuthUrl(@PathParam("redirectUrl") String redirectUrl) {
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
    public Result<?> authUrl(@Validated @RequestBody Code2SessionDto code2SessionDto) throws WxErrorException {
        WxMaJscode2SessionResult sessionResult = wxMaService.jsCode2SessionInfo(code2SessionDto.getCode());
        code2SessionDto.setCode(sessionResult.getOpenid());

        String token = super.createToken(code2SessionDto, passwordEncoder);

        Map<String, Object> map = new HashMap<>(2);
        map.put("sessionResult", sessionResult);
        map.put("token", token);

        return Result.ok(map);
    }

}
