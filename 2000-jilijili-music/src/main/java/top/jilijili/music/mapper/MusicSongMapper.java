package top.jilijili.music.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.music.entity.MusicSong;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【music_song(歌曲表)】的数据库操作Mapper
* @createDate 2023-07-13 19:22:40
* @Entity top.jilijili.music.pojo.entity.MusicSong
*/
@Mapper
public interface MusicSongMapper extends BaseMapper<MusicSong> {

}




