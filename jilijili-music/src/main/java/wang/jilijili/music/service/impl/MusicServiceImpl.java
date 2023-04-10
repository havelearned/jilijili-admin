package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.music.mapper.MusicMapper;
import wang.jilijili.music.pojo.bo.MusicConvertBo;
import wang.jilijili.music.pojo.dto.MusicDto;
import wang.jilijili.music.pojo.entity.Music;
import wang.jilijili.music.service.MusicService;

/**
 * @author admin
 * @description 针对表【music(歌词表)】的数据库操作Service实现
 * @createDate 2023-03-27 11:04:06
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
        implements MusicService {

    MusicMapper musicMapper;
    MusicConvertBo musicConvertBo;


    public MusicServiceImpl(MusicMapper musicMapper, MusicConvertBo musicConvertBo) {
        this.musicMapper = musicMapper;
        this.musicConvertBo = musicConvertBo;
    }

    @Override
    
    public MusicDto create(MusicDto musicDto) {
        Music music = this.musicConvertBo.toMusic(musicDto);
        int insert = this.musicMapper.insert(music);
        if (insert >= 1) {

            return this.musicConvertBo.toMusicDto(music);
        }

        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    @Override
    
    public MusicDto update(MusicDto musicDto) {
        Music music = this.musicConvertBo.toMusic(musicDto);
        int update = this.musicMapper.updateById(music);
        if (update >= 1) {

            return this.musicConvertBo.toMusicDto(music);
        }

        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }
}




