package top.jilijili.system.service;

import top.jilijili.system.entity.MusicSong;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.system.entity.dto.MusicSongDto;
import top.jilijili.system.entity.vo.MusicSongVo;

import java.io.Serializable;

/**
 * @author admin
 * @description 针对表【music_song(歌曲表)】的数据库操作Service
 * @createDate 2023-07-15 06:15:49
 */
public interface MusicSongService extends IService<MusicSong> {


    MusicSong addOrUpdateBefore(MusicSongDto musicSongDto);

    MusicSongVo selectSongInfoBySongId(Serializable songId);
}
