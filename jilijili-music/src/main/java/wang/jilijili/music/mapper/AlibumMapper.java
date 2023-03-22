package wang.jilijili.music.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import wang.jilijili.music.pojo.entity.Alibum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【alibum(专辑表)】的数据库操作Mapper
* @createDate 2023-03-21 15:15:44
* @Entity wang.jilijili.music.pojo.entity.Alibum
*/

@DS("slave_1")
@Mapper
public interface AlibumMapper extends BaseMapper<Alibum> {

}




