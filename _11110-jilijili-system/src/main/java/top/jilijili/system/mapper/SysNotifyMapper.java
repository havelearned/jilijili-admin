package top.jilijili.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.entity.SysNotify;
import top.jilijili.module.entity.vo.SysNotifyVo;

/**
 * @author admin
 * @description 针对表【sys_notify】的数据库操作Mapper
 * @createDate 2023-10-06 16:05:18
 * @Entity top.jilijili.module.entity.SysNotify
 */
@Mapper
public interface SysNotifyMapper extends BaseMapper<SysNotify> {

    IPage<SysNotifyVo> selectNotifyList(Page<SysNotifyVo> page, @Param(Constants.WRAPPER) Wrapper<SysNotify> ew);
}




