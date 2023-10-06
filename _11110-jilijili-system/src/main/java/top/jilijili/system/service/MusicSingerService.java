package top.jilijili.system.service;

import top.jilijili.module.entity.MusicSinger;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.MusicSong;
import top.jilijili.module.entity.vo.Result;

import java.io.Serializable;

/**
* @author admin
* @description 针对表【music_singer(歌手表)】的数据库操作Service
* @createDate 2023-07-06 22:10:20
*/
public interface MusicSingerService extends IService<MusicSinger> {

    Result<MusicSong> selectSingerByAllSong(Serializable singerId);
}
