package wang.jilijili.music.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import wang.jilijili.music.pojo.dto.MusicDto;
import wang.jilijili.music.pojo.entity.Music;

/**
 * @author admin
 * @description 针对表【music(歌词表)】的数据库操作Service
 * @createDate 2023-03-27 11:04:06
 */

@DS("slave_1")
public interface MusicService extends IService<Music> {

    MusicDto create(MusicDto musicDto);

    MusicDto update(MusicDto musicDto);
}
