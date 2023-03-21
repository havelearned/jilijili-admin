package wang.jilijili.music.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wang.jilijili.music.pojo.entity.Singer;

/**
 * @author admin
 * @description 针对表【singer(歌手表)】的数据库操作Mapper
 * @createDate 2023-03-20 22:54:49
 * @Entity wang.jilijili.music.pojo.entity.Singer
 */
@DS("slave_1")
@Mapper
public interface SingerMapper extends BaseMapper<Singer> {

}




