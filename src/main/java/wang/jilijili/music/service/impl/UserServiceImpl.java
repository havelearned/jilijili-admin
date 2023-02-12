package wang.jilijili.music.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wang.jilijili.music.common.utils.IpUtils;
import wang.jilijili.music.exception.BizException;
import wang.jilijili.music.exception.ExceptionType;
import wang.jilijili.music.mapper.UserMapper;
import wang.jilijili.music.pojo.convert.UserConvert;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.dto.UserQueryDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.Result;
import wang.jilijili.music.pojo.vo.UserVo;
import wang.jilijili.music.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-02-12 15:32:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    UserMapper userMapper;

    SessionRegistry sessionRegistry;
    private final UserConvert userConvert;

    PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserMapper userMapper, SessionRegistry sessionRegistry, UserConvert userConvert, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.sessionRegistry = sessionRegistry;
        this.userConvert = userConvert;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto get(String id) {
        User user = this.userMapper.selectById(id);
        return userConvert.toDto(user);
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        User user = this.userMapper.selectById(id);
        User updateUser = null;
        if (user != null) {
            updateUser = userConvert.toUserEntity(userUpdateRequest);
            this.userMapper.update(updateUser,
                    new LambdaQueryWrapper<User>().eq(User::getId, user.getId()));
        }

        return userConvert.toDto(updateUser);
    }

    @Override
    public Result<?> delete(String id) {
        int delete = this.userMapper.deleteById(id);

        return delete >= 1 ? Result.ok() : Result.fail();
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto, HttpServletRequest request) {
        User user = userConvert.toUserEntity(userCreateDto);
        user.setId(KsuidGenerator.generate());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLastLoginIp(IpUtils.getIpSource(IpUtils.getIpAddress(request)));
        user.setNickname(IpUtils.getUserAgent(request) + UUID.fastUUID().toString());


        this.userMapper.insert(user);
        return userConvert.toDto(user);

    }

    @Override
    public List<Object> getAllLoginUsers() {

        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object allPrincipal : allPrincipals) {
            if (allPrincipal instanceof String sessionId) {


            }
        }
        return allPrincipals;
    }

    @Override
    public IPage<UserVo> search(IPage<User> pageEntity, UserQueryDto userQueryDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(userQueryDto.getId()), User::getId, userQueryDto.getId())
                .like(StringUtils.hasText(userQueryDto.getGender()), User::getGender, userQueryDto.getGender())
                .like(StringUtils.hasText(userQueryDto.getUsername()), User::getUsername, userQueryDto.getUsername())
                .like(StringUtils.hasText(userQueryDto.getNickname()), User::getNickname, userQueryDto.getNickname())
                .eq(true, User::getUnseal, userQueryDto.getUnseal())
                .between(userQueryDto.getSpecifyTime() != null && userQueryDto.getCreatedTime() != null,
                        User::getCreatedTime, userQueryDto.getCreatedTime(), userQueryDto.getSpecifyTime());


        IPage<User> page = this.page(pageEntity, queryWrapper);


        return page.convert(item -> userConvert.toVo(userConvert.toDto(item)));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> eq = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = this.getOne(eq);
        if (user != null) {
            return user;
        }
        throw new BizException(ExceptionType.USERNAME_OR_PASSWORD_ERROR);

    }
}




