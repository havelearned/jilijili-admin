package top.jilijili.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.SysRoleMenu;

/**
* @author admin
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Mapper
* @createDate 2023-09-05 15:58:14
* @Entity top.jilijili.shop.entity.SysRoleMenu
*/
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}




