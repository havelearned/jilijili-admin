package wang.jilijili.common.core.service.impl;


import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang.jilijili.common.constant.DatabaseConstant;
import wang.jilijili.common.core.mapper.UserMapper;
import wang.jilijili.common.core.pojo.bo.UserConvertBo;
import wang.jilijili.common.core.pojo.dto.UserCreateDto;
import wang.jilijili.common.core.pojo.dto.UserDto;
import wang.jilijili.common.core.pojo.dto.UserQueryDto;
import wang.jilijili.common.core.pojo.entity.User;
import wang.jilijili.common.core.pojo.request.UserUpdateRequest;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.pojo.vo.UserVo;
import wang.jilijili.common.core.service.UserService;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.common.utils.IpUtils;

import java.util.List;

import static wang.jilijili.common.core.pojo.bo.UserSearchBo.getUserLambdaQueryWrapper;

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
    private final UserConvertBo userConvertBo;

    PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserMapper userMapper, SessionRegistry sessionRegistry, UserConvertBo userConvertBo, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.sessionRegistry = sessionRegistry;
        this.userConvertBo = userConvertBo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto get(String id) {
        User user = this.userMapper.selectById(id);
        return userConvertBo.toDto(user);
    }

    @Override
    public UserDto update(UserUpdateRequest userUpdateRequest) {
        User user = this.userConvertBo.toUserEntity(userUpdateRequest);
        if(user!=null){
            this.updateById(user);
            return userConvertBo.toDto(user);
        }
        throw new BizException(ExceptionType.BAD_REQUEST);


    }

    @Override
    public Result<?> delete(String id) {
        int delete = this.userMapper.deleteById(id);

        return delete >= 1 ? Result.ok() : Result.fail();
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NEVER)
    @Override
    public UserDto create(UserCreateDto userCreateDto, HttpServletRequest request) {
        User user = userConvertBo.toUserEntity(userCreateDto);
        user.setId(KsuidGenerator.generate());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLastLoginIp(IpUtils.getIpAddress(request));
        user.setNickname(IpUtils.getUserAgent(request) + UUID.fastUUID().toString());

        // 添加用户
        this.userMapper.insert(user);

        // 初始化用户权限,默认普通用户
        this.userMapper.initUserRole(user.getId(), DatabaseConstant.ROLE_USER_ID);
        return userConvertBo.toDto(user);

    }

    @Override
    public IPage<UserVo> getOnlineUsers(Long page, Long size, UserQueryDto userQueryDto) {
        IPage<User> ipage = new Page<>(page, size);
        // 得到在线用户
        List<Object> allPrincipals = this.sessionRegistry.getAllPrincipals();
        // 筛选条件
        LambdaQueryWrapper<User> queryWrapper = getUserLambdaQueryWrapper(userQueryDto);
        // 执行查询
        queryWrapper
                .in(allPrincipals.size() > 0, User::getUsername, allPrincipals);
        IPage<User> iPage = this.page(ipage, queryWrapper);
        // 列表转换
        return iPage.convert(item -> {
            UserDto userDto = userConvertBo.toDto(item);
            return userConvertBo.toVo(userDto);

        });
    }

    @Override
    public IPage<UserVo> search(IPage<User> pageEntity, UserQueryDto userQueryDto) {
        IPage<User> page = this.page(pageEntity, getUserLambdaQueryWrapper(userQueryDto));
        return page.convert(item -> userConvertBo.toVo(userConvertBo.toDto(item)));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userMapper.getUserByUsername(username);
        if (user != null) {
            return user;
        }
        throw new BizException(ExceptionType.USERNAME_OR_PASSWORD_ERROR);
    }


    @Override
    public UserDto currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.loadUserByUsername(authentication.getName());
        return userConvertBo.toDto(user);
    }

}




