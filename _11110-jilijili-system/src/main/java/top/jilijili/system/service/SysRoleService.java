package top.jilijili.system.service;

import top.jilijili.module.pojo.entity.sys.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【sys_role(角色表)】的数据库操作Service
* @createDate 2023-06-22 13:48:04
*/
public interface SysRoleService extends IService<SysRole> {

    Boolean removeRoleByRoleIds(List<Long> ids);
}
