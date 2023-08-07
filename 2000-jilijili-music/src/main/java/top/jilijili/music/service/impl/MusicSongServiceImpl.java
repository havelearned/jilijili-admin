package top.jilijili.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.music.entity.MusicSong;
import top.jilijili.music.service.MusicSongService;
import top.jilijili.music.mapper.MusicSongMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【music_song(歌曲表)】的数据库操作Service实现
* @createDate 2023-07-13 19:22:40
*/
@Service
public class MusicSongServiceImpl extends ServiceImpl<MusicSongMapper, MusicSong>
    implements MusicSongService{

}




