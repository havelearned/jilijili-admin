package top.jilijili.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.SysConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author admin
 * @description 针对表【sys_config(系统配置表)】的数据库操作Mapper
 * @createDate 2023-07-31 23:58:10
 * @Entity top.jilijili.module.entity.SysConfig
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig> {

}




