package wang.jilijili.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wang.jilijili.music.exception.BizException;
import wang.jilijili.music.exception.ExceptionType;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.convert.UserConvert;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Auther: Amani
 * @Date: 2023/1/24 11:43
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    private UserConvert userConvert;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionRegistry sessionRegistry;


    public UserServiceImpl(UserMapper userMapper, UserConvert userConvert, PasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.userConvert = userConvert;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDto> userList() {
        return userMapper.findAll().stream().map(userConvert::toDto).collect(Collectors.toList());
    }

    /**
     * 创建用户
     *
     * @param userCreateDto
     */
    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        checkUsername(userCreateDto.getUsername());
        User user = this.userConvert.toUserEntity(userCreateDto);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        user.setLocked(0);

        User save = this.userMapper.save(user);
        UserDto userDto = this.userConvert.toDto(save);

        return userDto;
    }


    @Override
    public UserDto get(String id) {
        Optional<User> user = this.userMapper.findById(id);
        if (user.isPresent()) {
            UserDto userDto = this.userConvert.toDto(user.get());
            return userDto;
        }
        return new UserDto();
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        try {
            Optional<User> user = this.userMapper.findById(id);
            if (!user.isPresent()) {
                throw new BizException(ExceptionType.USER_NOT_FOND);
            }

            User saveUser = this.userConvert.toUserEntity(userUpdateRequest);
            saveUser.setId(user.get().getId());
            User resultUser = this.userMapper.save(saveUser);
            UserDto userDto = this.userConvert.toDto(resultUser);
            return userDto;
        } catch (Exception e) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }

    }

    @Override
    public Result<?> delete(String id) {
        Optional<User> user = this.userMapper.findById(id);
        if (user.isPresent()) {
            this.userMapper.deleteById(id);
            return Result.ok();
        }
        return Result.fail(ExceptionType.USER_NOT_FOND.getMessage());


    }

    @Override
    public Page<UserDto> search(Pageable pageable) {

        return this.userMapper.findAll(pageable).map(userConvert::toDto);
    }


    public void checkUsername(String username) {
        Optional<User> user = this.userMapper.findUserByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }


    @Override
    public List<UserDto> getAllLoginUsers() {
        List<Object> allPrincipals = this.sessionRegistry.getAllPrincipals();
        List<UserDto> userDtos = allPrincipals.stream().map(username -> {
            Optional<User> byUsername = this.userMapper.findUserByUsername(username.toString());
            return userConvert.toDto(byUsername.get());
        }).collect(Collectors.toList());
        return userDtos;
    }

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userMapper.findUserByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }
        throw new BizException(ExceptionType.USER_NOT_FOND);
    }

}
