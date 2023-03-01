package wang.jilijili.music.service;


import wang.jilijili.music.pojo.dto.CreateWeChatTokenDto;
import wang.jilijili.music.pojo.dto.UserDto;

public interface WeChatMPService {
    UserDto createToken(CreateWeChatTokenDto createWeChatTokenDto);
}
