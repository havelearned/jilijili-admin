package wang.jilijili.common.core.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wang.jilijili.common.constant.DatabaseConstant;
import wang.jilijili.common.core.mapper.UserMapper;
import wang.jilijili.common.core.pojo.bo.UserConvertBo;
import wang.jilijili.common.core.pojo.dto.UserCreateDto;
import wang.jilijili.common.core.pojo.dto.UserDto;
import wang.jilijili.common.core.pojo.dto.UserQueryDto;
import wang.jilijili.common.core.pojo.entity.SysMenu;
import wang.jilijili.common.core.pojo.entity.User;
import wang.jilijili.common.core.pojo.request.UserUpdateRequest;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.core.pojo.vo.UserVo;
import wang.jilijili.common.core.service.RedisService;
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
    UserConvertBo userConvertBo;

    PasswordEncoder passwordEncoder;

    RedisService redisService;

    StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    @Autowired
    public void setUserConvertBo(UserConvertBo userConvertBo) {
        this.userConvertBo = userConvertBo;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public UserDto get(String id) {
        User user = this.userMapper.selectById(id);
        return userConvertBo.toDto(user);
    }

    @Override


    public UserDto update(UserUpdateRequest userUpdateRequest) {
        User user = this.userConvertBo.toUserEntity(userUpdateRequest);
        if (user != null) {
            this.userMapper.updateById(user);
            return userConvertBo.toDto(user);
        }
        throw new BizException(ExceptionType.BAD_REQUEST);


    }

    @Override


    public Result<?> delete(String id) {
        int delete = this.userMapper.deleteById(id);

        return delete >= 1 ? Result.ok() : Result.fail();
    }

    @Override

    public UserDto create(UserCreateDto userCreateDto, HttpServletRequest request) {
        User user = userConvertBo.toUserEntity(userCreateDto);
        user.setId(KsuidGenerator.generate());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLastLoginIp(IpUtils.getIpAddress(request));
        if (!StringUtils.hasText(user.getNickname())) {
            user.setNickname(
                    String.format("%s-%s", IpUtils.getUserAgent(request), UUID.randomUUID().toString()));
        }

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
    public IPage<UserVo> search(IPage<UserVo> pageEntity, UserQueryDto userQueryDto) {
        UserVo userVo = this.userConvertBo.toVo(userQueryDto);
        QueryWrapper<UserVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(userVo.getUsername()), "username", userVo.getUsername())
                .like(StringUtils.hasText(userVo.getNickname()), "nickname", userVo.getNickname())
                .eq(StringUtils.hasText(userVo.getGender()), "gender", userVo.getGender())
                .eq(userQueryDto.getUnseal() != null, "enabled", userQueryDto.getUnseal())
                .between(userQueryDto.getSpecifyTime() != null && userQueryDto.getCreatedTime() != null,
                        "created_time", userQueryDto.getCreatedTime(), userQueryDto.getSpecifyTime())
                .eq("locked", 0)
                .orderBy(true, false, "u.created_time");
        List<UserVo> list = this.userMapper.pageQuery(pageEntity, queryWrapper);
        pageEntity.setRecords(list);
        return pageEntity;
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


    @Override
    public List<Tree<String>> getMenu(String userId) {

        List<SysMenu> menuList = this.userMapper.getMenu(userId);

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setWeightKey("orderNum");
        treeNodeConfig.setIdKey("menuId");
        treeNodeConfig.setNameKey("menuName");
        treeNodeConfig.setParentIdKey("parentId");
        treeNodeConfig.setChildrenKey("children");

        return TreeUtil.build(menuList, "0", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(Convert.toStr(treeNode.getMenuId()));
            tree.setParentId(Convert.toStr(treeNode.getParentId()));
            tree.setName(Convert.toStr(treeNode.getMenuName()));
            tree.setWeight(treeNode.getOrderNum());

            tree.putExtra("path", treeNode.getPath());
            tree.putExtra("component", treeNode.getComponent());
            tree.putExtra("isFrame", treeNode.getIsFrame());
            tree.putExtra("isCache", treeNode.getIsCache());
            tree.putExtra("menuType", treeNode.getMenuType());
            tree.putExtra("visible", treeNode.getVisible());
            tree.putExtra("status", treeNode.getStatus());
            tree.putExtra("perms", treeNode.getPerms());
            tree.putExtra("icon", treeNode.getIcon());
            tree.putExtra("remark", treeNode.getRemark());
        });

    }
}




