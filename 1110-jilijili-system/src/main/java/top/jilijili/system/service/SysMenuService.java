package top.jilijili.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.system.entity.SysMenu;
import top.jilijili.system.entity.dto.SysMenuDto;
import top.jilijili.system.entity.vo.SysMenuVo;

/**
* @author admin
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-06-22 22:03:11
*/
public interface SysMenuService extends IService<SysMenu> {


    IPage<SysMenuVo> selectAll(SysMenuDto sysMenuDto);
}
