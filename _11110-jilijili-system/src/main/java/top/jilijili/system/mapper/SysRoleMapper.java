package top.jilijili.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author admin
 * @description 针对表【sys_role(角色表)】的数据库操作Mapper
 * @createDate 2023-06-22 13:48:04
 * @Entity top.jilijili.system.pojo.entity.SysRole
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

}




