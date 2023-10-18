package top.jilijili.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.pojo.entity.music.MusicSong;
import top.jilijili.module.pojo.vo.music.MusicSongVo;

import java.io.Serializable;

/**
 * @author admin
 * @description 针对表【music_song(歌曲表)】的数据库操作Mapper
 * @createDate 2023-07-15 06:15:49
 * @Entity top.jilijili.system.pojo.entity.MusicSong
 */
@Mapper
public interface MusicSongMapper extends BaseMapper<MusicSong> {

    MusicSongVo querySongInfoById(@Param(value = "songId") Serializable songId);

}




