package wang.jilijili.music.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import jakarta.websocket.server.PathParam;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wang.jilijili.music.pojo.dto.Code2SessionDto;
import wang.jilijili.music.pojo.vo.Result;

/**
 * @author Amani
 * @date 2023年02月21日 17:04
 */
@RestController
@RequestMapping("/weChat")
public class WeixinController {


    WxMaService wxMaService;


    public WeixinController(WxMaService wxMaService) {

        this.wxMaService = wxMaService;
    }

    /**
     * @param redirectUrl 重定向地址
     * @return null
     * @author Amani
     * @date 2023/2/25 10:04
     */
    @GetMapping("/auth_url")
    public String getAuthUrl(@PathParam("redirectUrl") String redirectUrl) {
        return redirectUrl;
    }

    /**
     * 获取小程序openId等信息
     *
     * @param code2SessionDto code 和账号密码
     * @return 返回用户信息
     * @throws WxErrorException
     */
    @PostMapping("/")
    public Result<?> authUrl(@Validated @RequestBody Code2SessionDto code2SessionDto) throws WxErrorException {
        WxMaJscode2SessionResult sessionResult = wxMaService.jsCode2SessionInfo(code2SessionDto.getCode());
        return Result.ok(sessionResult);
    }

}
