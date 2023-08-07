package top.jilijili.common.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.common.core.pojo.entity.SysDictType;
import top.jilijili.common.core.pojo.vo.DictTypeVO;

/**
* @author admin
* @description 针对表【sys_dict_type(字典类型表)】的数据库操作Mapper
* @createDate 2023-04-29 11:18:36
* @Entity wang.jilijili.music.pojo.entity.SysDictType
*/

@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {


    /**
     * 查询后按DictTyp查询
     * @param dictType 字典类型
     * @return 字典数据
     */
    DictTypeVO queryByDictTypeAfter(@Param(value = "dictType") String dictType);
}




