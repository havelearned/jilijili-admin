package top.jilijili.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import top.jilijili.module.pojo.entity.sys.SysMenu;
import top.jilijili.module.pojo.entity.sys.SysUser;
import top.jilijili.module.pojo.dto.sys.SysRoleDto;
import top.jilijili.module.pojo.dto.sys.SysUserDto;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.vo.sys.SysRoleVo;
import top.jilijili.module.pojo.vo.sys.SysUserVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2023-06-22 01:07:41
 */
public interface SysUserService extends IService<SysUser> {



    SysUserVo addSysUser(SysUserDto sysUserDto, HttpServletRequest request);

    List<SysMenu> getMenuList(Long userId);

    Result login(SysUserDto sysUserDto);

    List<SysRoleVo>  getUserRoleInformation(Serializable id);

    Boolean updateUserRole(Long userId, List<SysRoleDto> ownedItems);
}
