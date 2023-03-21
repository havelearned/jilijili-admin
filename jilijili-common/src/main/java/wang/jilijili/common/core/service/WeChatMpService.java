package wang.jilijili.common.core.service;


import wang.jilijili.common.core.pojo.dto.CreateWeChatTokenDto;
import wang.jilijili.common.core.pojo.dto.UserDto;

/**
 * @author admin
 */
public interface WeChatMpService {
   /**
    * 登录接口
    * @author Amani
    * @date 2023/3/5 11:24
    * @param createWeChatTokenDto 创建微信token条件
    * @return wang.jilijili.music.pojo.dto.UserDto
    */
    UserDto createToken(CreateWeChatTokenDto createWeChatTokenDto);
}
