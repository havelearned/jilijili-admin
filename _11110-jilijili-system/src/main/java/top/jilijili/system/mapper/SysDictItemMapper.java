package top.jilijili.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.SysDictItem;

/**
* @author admin
* @description 针对表【sys_dict_item(字典item表)】的数据库操作Mapper
* @createDate 2023-10-06 14:40:49
* @Entity top.jilijili.module.entity.SysDictItem
*/
@Mapper
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {

}




