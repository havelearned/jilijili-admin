package top.jilijili.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.SysDict;

/**
* @author admin
* @description 针对表【sys_dict(字典表)】的数据库操作Mapper
* @createDate 2023-09-05 08:57:32
* @Entity top.jilijili.module.entity.SysDict
*/

@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {

}




