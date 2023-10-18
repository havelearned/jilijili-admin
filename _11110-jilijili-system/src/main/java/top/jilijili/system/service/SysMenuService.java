package top.jilijili.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.pojo.entity.sys.SysMenu;
import top.jilijili.module.pojo.dto.sys.SysMenuDto;
import top.jilijili.module.pojo.dto.sys.SysRoleMenuDto;
import top.jilijili.module.pojo.vo.sys.SysMenuVo;

import java.io.Serializable;
import java.util.Map;

/**
* @author admin
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-06-22 22:03:11
*/
public interface SysMenuService extends IService<SysMenu> {


    IPage<SysMenuVo> selectAll(SysMenuDto sysMenuDto);

    Map<String, Object> getRoleMenuList(Serializable id);

    boolean bindingMenuAndRole(SysRoleMenuDto sysRoleDto);
}
