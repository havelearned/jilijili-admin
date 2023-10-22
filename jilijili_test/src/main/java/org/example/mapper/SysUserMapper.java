package org.example.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import top.jilijili.module.pojo.entity.sys.SysUser;

/**
 * @author admin
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @createDate 2023-06-22 01:07:41
 * @Entity top.jilijili.system.pojo.entity.SysUser
 */
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 60 * 1000)
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {




}




