package top.jilijili.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.pojo.entity.sys.SysMenu;
import top.jilijili.module.pojo.vo.sys.SysMenuVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
 * @createDate 2023-06-22 22:03:11
 * @Entity top.jilijili.module.entity.SysMenu
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {


    /**
     * 通过角色id获取菜单列表
     *
     * @param roleId
     * @return
     */
    List<SysMenuVo> queryRoleMenuListByRoleId(@Param(("roleId")) Serializable roleId);


}




