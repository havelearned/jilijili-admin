package wang.jilijili.music.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amani
 * @date 2023年02月25日 9:05
 */
@Configuration
public class WxChatConfig {


    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Bean
    public WxMaService wxMpService() {
        WxMaDefaultConfigImpl wxMaDefaultConfig = new WxMaDefaultConfigImpl();
        wxMaDefaultConfig.setAppid(appId);
        wxMaDefaultConfig.setSecret(appSecret);
        WxMaService maService = new WxMaServiceImpl();
        maService.setWxMaConfig(wxMaDefaultConfig);
        return maService;
    }


}
