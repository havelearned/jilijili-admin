package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.module.pojo.entity.sys.SysConfig;
import top.jilijili.system.service.SysConfigService;
import top.jilijili.system.mapper.SysConfigMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【sys_config(系统配置表)】的数据库操作Service实现
* @createDate 2023-07-31 23:58:10
*/
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig>
    implements SysConfigService{

}




