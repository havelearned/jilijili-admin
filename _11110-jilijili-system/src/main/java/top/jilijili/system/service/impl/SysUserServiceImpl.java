package top.jilijili.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.jilijili.system.common.enums.RoleEnum;
import top.jilijili.system.common.utils.IpUtils;
import top.jilijili.system.common.utils.PasswordUtils;
import top.jilijili.system.common.utils.RandomNumberUtils;
import top.jilijili.system.entity.SysMenu;
import top.jilijili.system.entity.SysUser;
import top.jilijili.system.entity.SysUserRole;
import top.jilijili.system.entity.dto.SysRoleDto;
import top.jilijili.system.entity.dto.SysUserDto;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.entity.vo.SysRoleVo;
import top.jilijili.system.entity.vo.SysUserVo;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.mapper.SysUserMapper;
import top.jilijili.system.service.SysRoleService;
import top.jilijili.system.service.SysUserRoleService;
import top.jilijili.system.service.SysUserService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-06-22 01:07:41
 */
@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    private ConvertMapper convertMapper;
    private SysRoleService sysRoleService;
    private SysUserRoleService sysUserRoleService;
    private SysUserMapper sysUserMapper;


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public SysUserVo addSysUser(SysUserDto sysUserDto, HttpServletRequest request) {
        SysUser sysUserEntity = convertMapper.toSysUserEnetity(sysUserDto);

        // 设置默认昵称
        sysUserEntity.setNickname(sysUserDto.getLoginType().getOption() + RandomNumberUtils.generateRandomNumber(1, 6));
        // 密码加密
        sysUserEntity.setPassword(PasswordUtils.encryptPassword(sysUserEntity.getPassword()));
        // 设置ip
        sysUserEntity.setLastLoginIp(IpUtils.getIpSource(IpUtils.getIpAddress(request)));
        boolean save = this.save(sysUserEntity);
        if (save) {
            // 设置默认角色
            SysUserRole userRole = SysUserRole.builder()
                    .userId(sysUserEntity.getUserId())
                    .roleId(RoleEnum.SYS_USER.getId())
                    .build();
            this.sysUserRoleService.save(userRole);
        }
        return convertMapper.toSysUserVo(sysUserEntity);
    }

    @Override
    public List<SysMenu> getMenuList(Long userId) {
        return this.sysUserMapper.queryMenuByUserId(userId);
    }

    @Override
    public Result<?> login(SysUserDto sysUserDto) {
        SysUser sysUserEntity = this.convertMapper.toSysUserEnetity(sysUserDto);
        // TODO 验证 验证码
        String token;
        SysUser currentUser = this.lambdaQuery()
                .eq(SysUser::getUsername, sysUserEntity.getUsername()).one();

        if (currentUser != null && PasswordUtils.verifyPassword(sysUserEntity.getPassword(), currentUser.getPassword())) {
            StpUtil.login(currentUser.getUserId());
            token = StpUtil.getTokenValueByLoginId(currentUser.getUserId());
            log.info("用户=>{}-{}-{}登录", currentUser.getUsername(), currentUser.getUserId(), currentUser.getUserType());
            return Result.ok(token);
        }
        return Result.fail(200, "登录失败!账号或者密码错误!!!");
    }

    @Override
    public List<SysRoleVo> getUserRoleInformation(Serializable id) {
        return this.sysUserMapper.queryRoleByUserId(id);
    }

    @Override
    public Boolean updateUserRole(Long userId, List<SysRoleDto> ownedItems) {
        Map<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("user_id", userId);
        // 根据userid删除关联内容
        boolean remove = this.sysUserRoleService.removeByMap(hashMap);
        if (remove) {
            // 收集RoleId, 映射构建SysUserRole对象,最后转list批量添加
            List<SysUserRole> roleList = ownedItems.stream().map(SysRoleDto::getRoleId)
                    .map(roleId -> SysUserRole.builder().userId(userId).roleId(roleId).build())
                    .collect(Collectors.toList());
            return this.sysUserRoleService.saveBatch(roleList);
        }
        return false;
    }

}




