package top.jilijili.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import top.jilijili.module.entity.SysMenu;
import top.jilijili.module.entity.SysUser;
import top.jilijili.module.entity.vo.SysRoleVo;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @createDate 2023-06-22 01:07:41
 * @Entity top.jilijili.system.pojo.entity.SysUser
 */
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 60 * 1000)
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {


    List<SysMenu> queryMenuByUserId(@Param(value = "userId") Long userId);

    List<SysRoleVo> queryRoleByUserId(@Param(value = "userId") Serializable userId);

}




