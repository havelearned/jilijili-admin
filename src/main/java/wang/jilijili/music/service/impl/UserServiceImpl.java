package wang.jilijili.music.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.convert.UserConvert;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.service.UserService;

import java.util.List;

/**
 * @Auther: Amani
 * @Date: 2023/1/24 11:43
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private UserConvert userConvert;


    public UserServiceImpl(UserMapper userMapper, UserConvert userConvert) {
        this.userMapper = userMapper;
        this.userConvert = userConvert;
    }


    @Override
    public List<UserDto> userList() {
        return userMapper.findAll().stream().map(userConvert::toDto).toList();
    }
}
