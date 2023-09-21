package wang.jilijili.musics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.MusicSinger;

/**
* @author admin
* @description 针对表【music_singer(歌手表)】的数据库操作Mapper
* @createDate 2023-07-06 22:10:20
* @Entity top.jilijili.system.pojo.entity.MusicSinger
*/
@Mapper
public interface MusicSingerMapper extends BaseMapper<MusicSinger> {

}




