package wang.jilijili.musics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.entity.music.MusicSinger;
import top.jilijili.module.pojo.entity.music.MusicSong;
import wang.jilijili.musics.mapper.MusicSingerMapper;
import wang.jilijili.musics.mapper.MusicSongMapper;
import wang.jilijili.musics.service.MusicSingerService;

import java.io.Serializable;

/**
* @author admin
* @description 针对表【music_singer(歌手表)】的数据库操作Service实现
* @createDate 2023-07-06 22:10:20
*/
@Service
@AllArgsConstructor
public class MusicSingerServiceImpl extends ServiceImpl<MusicSingerMapper, MusicSinger>
    implements MusicSingerService {

    private MusicSongMapper songMapper;

    @Override
    public Result<MusicSong> selectSingerByAllSong(Serializable singerId) {

        return null;
    }
}




