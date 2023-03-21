package wang.jilijili.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wang.jilijili.music.pojo.dto.SingerDto;
import wang.jilijili.music.pojo.entity.Singer;

/**
* @author admin
* @description 针对表【singer(歌手表)】的数据库操作Service
* @createDate 2023-03-20 22:54:49
*/
public interface SingerService extends IService<Singer> {


    SingerDto create(SingerDto singerDto);

    SingerDto update(SingerDto singer);
}
