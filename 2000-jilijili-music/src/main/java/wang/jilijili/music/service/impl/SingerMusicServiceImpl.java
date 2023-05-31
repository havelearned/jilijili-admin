package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import wang.jilijili.music.pojo.entity.SingerMusic;
import wang.jilijili.music.service.SingerMusicService;
import wang.jilijili.music.mapper.SingerMusicMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【singer_music(歌手和歌曲的中间表)】的数据库操作Service实现
* @createDate 2023-04-13 15:12:42
*/
@Service
public class SingerMusicServiceImpl extends ServiceImpl<SingerMusicMapper, SingerMusic>
    implements SingerMusicService{

}




