package top.jilijili.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import top.jilijili.module.pojo.entity.sys.SysMenu;
import top.jilijili.module.pojo.entity.sys.SysUser;
import top.jilijili.module.pojo.vo.sys.SysRoleVo;

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


    @Select("select count(u.user_id) as `value` from sys_user u;")
    Long queryUserCount();

    @Select("""
                SELECT CURDATE() - INTERVAL 4 DAY as xDate
                UNION ALL
                SELECT CURDATE() - INTERVAL 3 DAY as xDate
                UNION ALL
                SELECT CURDATE() - INTERVAL 2 DAY as xDate
                UNION ALL
                SELECT CURDATE() - INTERVAL 1 DAY as xDate
                UNION ALL
                SELECT CURDATE() as xDate;
                """ )
    List<String> queryDate();

    @Select("""
                select if(count(u.created_time) <= 0, 0, count(u.created_time)) as seriesData
                from sys_user u
                where u.created_time between Now() - INTERVAL 5 DAY and now()
                group by u.created_time;
                """)
    List<Long> querySeriesData();

    @Select("""
                SELECT AVG(seriesData) as avg
                FROM (  select if(count(u.created_time) <= 0, 0, count(u.created_time)) as seriesData
                         from sys_user u
                         where u.created_time between Now() - INTERVAL 5 DAY and now()
                         group by u.created_time ) AS subquery;
                """)
    Long queryAvg();

}




