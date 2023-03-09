package wnag.jilijili.music.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import wnag.jilijili.music.pojo.dto.MusicDto;
import wnag.jilijili.music.pojo.entity.Music;
import wnag.jilijili.music.pojo.request.MusicCreateRequest;
import wnag.jilijili.music.pojo.request.MusicQueryRequest;
import wnag.jilijili.music.pojo.request.MusicUpdateRequest;

/**
 * @author admin
 * @description 针对表【music】的数据库操作Service
 * @createDate 2023-03-09 10:14:58
 */
public interface MusicService extends IService<Music> {
    MusicDto create(MusicCreateRequest musicCreateRequest);

    MusicDto update(MusicUpdateRequest musicUpdateRequest);

    void delete(String id);

    IPage<MusicDto> search(IPage<Music> tPage, MusicQueryRequest musicQueryRequest);
}
