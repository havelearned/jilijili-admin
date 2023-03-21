package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import org.springframework.stereotype.Service;
import wang.jilijili.common.enums.StatusCodeEnum;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.music.mapper.MusicMapper;
import wang.jilijili.music.pojo.bo.MusicConvertBo;
import wang.jilijili.music.pojo.dto.MusicDto;
import wang.jilijili.music.pojo.entity.Music;
import wang.jilijili.music.pojo.request.MusicCreateRequest;
import wang.jilijili.music.pojo.request.MusicQueryRequest;
import wang.jilijili.music.pojo.request.MusicUpdateRequest;
import wang.jilijili.music.service.MusicService;

import static wang.jilijili.music.pojo.bo.MusicSearchBo.getMusicLambdaQueryWrapper;

/**
 * @author admin
 * @description 针对表【music】的数据库操作Service实现
 * @createDate 2023-03-09 10:14:58
 */

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
        implements MusicService {

    MusicConvertBo musicConvertBo;

    MusicMapper musicMapper;


    public MusicServiceImpl(MusicConvertBo musicConvertBo, MusicMapper musicMapper) {
        this.musicConvertBo = musicConvertBo;
        this.musicMapper = musicMapper;
    }

    @Override
    public MusicDto create(MusicCreateRequest musicCreateRequest) {
        Music music = musicConvertBo.toMusic(musicCreateRequest);
        music.setId(KsuidGenerator.generate());
        if (this.save(music)) {
            return this.musicConvertBo.toMusicDto(music);
        }
        throw new BizException(StatusCodeEnum.FAIL);
    }


    @Override
    public MusicDto update(MusicUpdateRequest musicUpdateRequest) {
        Music music = this.musicConvertBo.toMusic(musicUpdateRequest);
        if (this.updateById(music)) {
            return this.musicConvertBo.toMusicDto(music);
        }
        throw new BizException(StatusCodeEnum.FAIL);
    }

    @Override
    public void delete(String id) {
        if (!this.removeById(id)) {
            throw new BizException(StatusCodeEnum.FAIL);
        }
    }

    @Override
    public IPage<MusicDto> search(IPage<Music> tPage, MusicQueryRequest musicQueryRequest) {
//        IPage<Music> musicIpage = this.page(tPage, getMusicLambdaQueryWrapper(musicQueryRequest));
        IPage<Music> musicIpage = this.musicMapper.queryAllMusicInformation(tPage, getMusicLambdaQueryWrapper(musicQueryRequest));
        return musicIpage.convert((item) -> this.musicConvertBo.toMusicDto(item));

    }
}




