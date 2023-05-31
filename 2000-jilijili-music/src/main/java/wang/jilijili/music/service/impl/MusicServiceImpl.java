package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.music.mapper.MusicMapper;
import wang.jilijili.music.mapper.SingerMusicMapper;
import wang.jilijili.music.pojo.bo.MusicConvertBo;
import wang.jilijili.music.pojo.dto.MusicDto;
import wang.jilijili.music.pojo.entity.Music;
import wang.jilijili.music.pojo.entity.SingerMusic;
import wang.jilijili.music.pojo.vo.MusicDetailVo;
import wang.jilijili.music.service.MusicService;
import wang.jilijili.music.service.SingerMusicService;

import java.util.List;

/**
 * @author admin
 * @description 针对表【music(歌词表)】的数据库操作Service实现
 * @createDate 2023-03-27 11:04:06
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

    MusicMapper musicMapper;
    MusicConvertBo musicConvertBo;

    SingerMusicService singerMusicService;
    SingerMusicMapper singerMusicMapper;

    @Autowired
    public MusicServiceImpl(MusicMapper musicMapper, MusicConvertBo musicConvertBo, SingerMusicService singerMusicService, SingerMusicMapper singerMusicMapper) {
        this.musicMapper = musicMapper;
        this.musicConvertBo = musicConvertBo;
        this.singerMusicService = singerMusicService;
        this.singerMusicMapper = singerMusicMapper;
    }


    @Override
    public IPage<MusicDto> listPage(MusicDto musicDto, IPage<Music> iPage) {
        Music music = this.musicConvertBo.toMusic(musicDto);
        if (StringUtils.hasText(musicDto.getKey())) {
            music.setName(musicDto.getKey());
        }
        iPage = this.musicMapper.selectPage(iPage, new LambdaQueryWrapper<Music>().eq(StringUtils.hasText(music.getId()), Music::getId, music.getId()).like(StringUtils.hasText(music.getName()), Music::getName, music.getName()).eq(music.getStatus() != null, Music::getStatus, music.getStatus())
                .orderBy(true, false, Music::getCreatedTime)

        );
        return iPage.convert(item -> this.musicConvertBo.toMusicDto(item));

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public MusicDto create(MusicDto musicDto) {
        //  添加歌曲
        Music music = this.musicConvertBo.toMusic(musicDto);
        this.save(music);

        // 添加歌手歌曲关联表
        return getSingerMusicList(musicDto, music);
    }

    @Override
    public MusicDto update(MusicDto musicDto) {
        Music music = this.musicConvertBo.toMusic(musicDto);
        int update = this.musicMapper.updateById(music);
        if (update >= 1) {
            this.singerMusicService.remove(new LambdaQueryWrapper<SingerMusic>().eq(SingerMusic::getMusicId, music.getId()));
        }
        // 添加歌曲歌手关联表信息
        return getSingerMusicList(musicDto, music);
    }

    @Override
    public MusicDetailVo queryMusicInfoById(String id) {
        return this.musicMapper.queryMusicInfoById(id);

    }

    @Override
    public Boolean deletedByIds(List<String> idList) {
        // 通过歌曲id列表删除歌曲歌手中间表信息
        LambdaQueryWrapper<SingerMusic> queryWrapper = new LambdaQueryWrapper<SingerMusic>().in(!CollectionUtils.isEmpty(idList), SingerMusic::getMusicId, idList);
        this.singerMusicService.remove(queryWrapper);
        // 通过歌曲id列表删除歌曲信息
        int row = this.musicMapper.deleteBatchIds(idList);

        return row >= 1;
    }

    /**
     * 通过多个歌手id添加到 歌曲歌手中间
     *
     * @param musicDto 包含有多个歌手id的dto
     * @param music    包含有歌曲id的实体类
     * @return dto
     */
    private MusicDto getSingerMusicList(MusicDto musicDto, Music music) {
        List<SingerMusic> singerMusicList = musicDto.getSingerId().stream().map(item -> new SingerMusic(item, music.getId())).toList();
        boolean b = this.singerMusicService.saveBatch(singerMusicList);
        if (b) {
            return this.musicConvertBo.toMusicDto(music);
        }
        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }
}




