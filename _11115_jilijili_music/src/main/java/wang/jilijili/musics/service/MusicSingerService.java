package wang.jilijili.musics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.common.entity.Result;
import top.jilijili.module.entity.MusicSinger;
import top.jilijili.module.entity.MusicSong;

import java.io.Serializable;

/**
* @author admin
* @description 针对表【music_singer(歌手表)】的数据库操作Service
* @createDate 2023-07-06 22:10:20
*/
public interface MusicSingerService extends IService<MusicSinger> {

    Result<MusicSong> selectSingerByAllSong(Serializable singerId);
}
