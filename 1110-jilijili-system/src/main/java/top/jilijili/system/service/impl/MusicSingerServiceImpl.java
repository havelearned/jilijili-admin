package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.jilijili.system.entity.MusicSinger;
import top.jilijili.system.entity.MusicSong;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.mapper.MusicSingerMapper;
import top.jilijili.system.mapper.MusicSongMapper;
import top.jilijili.system.service.MusicSingerService;

import java.io.Serializable;

/**
* @author admin
* @description 针对表【music_singer(歌手表)】的数据库操作Service实现
* @createDate 2023-07-06 22:10:20
*/
@Service
@AllArgsConstructor
public class MusicSingerServiceImpl extends ServiceImpl<MusicSingerMapper, MusicSinger>
    implements MusicSingerService{

    private MusicSongMapper songMapper;

    @Override
    public Result<MusicSong> selectSingerByAllSong(Serializable singerId) {

        return null;
    }
}




