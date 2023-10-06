package top.jilijili.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author admin
 * @description 针对表【sys_user_role(用户_角色中间表)】的数据库操作Mapper
 * @createDate 2023-06-22 14:38:39
 * @Entity top.jilijili.system.pojo.entity.SysUserRole
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}




