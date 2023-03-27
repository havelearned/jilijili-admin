package wang.jilijili.music.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wang.jilijili.music.pojo.entity.Music;

/**
 * @author admin
 * @description 针对表【music(歌词表)】的数据库操作Mapper
 * @createDate 2023-03-27 11:04:06
 * @Entity wang.jilijili.music.pojo.entity.Music
 */
@DS("slave_1")
@Mapper
public interface MusicMapper extends BaseMapper<Music> {

}




