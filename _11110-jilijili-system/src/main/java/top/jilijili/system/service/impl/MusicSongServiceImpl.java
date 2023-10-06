package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.jilijili.module.entity.MusicAlbum;
import top.jilijili.module.entity.MusicSinger;
import top.jilijili.module.entity.MusicSong;
import top.jilijili.module.entity.dto.ChooseEntityDto;
import top.jilijili.module.entity.dto.MusicSongDto;
import top.jilijili.module.entity.vo.MusicAlbumVo;
import top.jilijili.module.entity.vo.MusicSingerVo;
import top.jilijili.module.entity.vo.MusicSongVo;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.mapper.MusicSongMapper;
import top.jilijili.system.service.MusicAlbumService;
import top.jilijili.system.service.MusicSingerService;
import top.jilijili.system.service.MusicSongService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【music_song(歌曲表)】的数据库操作Service实现
 * @createDate 2023-07-15 06:15:49
 */
@Service
@AllArgsConstructor
public class MusicSongServiceImpl extends ServiceImpl<MusicSongMapper, MusicSong> implements MusicSongService {

    private MusicSongMapper musicSongMapper;
    private ConvertMapper convertMapper;
    private MusicSingerService musicSingerService;
    private MusicAlbumService musicAlbumService;


    /**
     * 添加或者修改前操作
     *
     * @param musicSongDto 操作对象
     * @return 实体类
     */
    public MusicSong addOrUpdateBefore(MusicSongDto musicSongDto) {
        // 拼接多个歌手id
        String singerIds = musicSongDto.getSingerList()
                .stream().map(ChooseEntityDto::getValue).collect(Collectors.joining(", "));

        MusicSong songEntity = this.convertMapper.toSongEntity(musicSongDto);
        songEntity.setSingerIds(singerIds);

        String albumId = "";
        if (!CollectionUtils.isEmpty(musicSongDto.getAlbum())) {
            albumId = musicSongDto.getAlbum().get(0).getValue();
            songEntity.setAlbumId(Long.parseLong(albumId));
        }

        if (!StringUtils.hasText(songEntity.getLyric())) {
            songEntity.setLyric("暂无歌词");
        }

        return songEntity;
    }

    ;


    /**
     * 通过歌曲id查询歌手,专辑信息
     *
     * @param songId
     * @return
     */
    @Override
    public MusicSongVo selectSongInfoBySongId(Serializable songId) {
        // 查询
        MusicSong musicSong = this.musicSongMapper.selectById(songId);
        if (musicSong == null) {
            return new MusicSongVo();
        }
        // 转vo
        MusicSongVo songVo = this.convertMapper.toSongVo(musicSong);

        // 查询歌曲信息
        List<MusicSingerVo> singerVos = null;
        if (StringUtils.hasText(songVo.getSingerIds())) {
            String[] strings = musicSong.getSingerIds().replace(" ", "").split(",");
            List<String> singerIds = Arrays.asList(strings);
            LambdaQueryWrapper<MusicSinger> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(!musicSong.getSingerIds().isEmpty(), MusicSinger::getSingerId, singerIds);
            singerVos = this.musicSingerService.list(queryWrapper)
                    .stream().map(this.convertMapper::toSingerVo).collect(Collectors.toList());
        }

        // 查询专辑信息
        MusicAlbumVo albumVo = null;
        if (musicSong.getAlbumId() != null) {
            MusicAlbum album = this.musicAlbumService.lambdaQuery()
                    .eq(musicSong.getAlbumId() != null, MusicAlbum::getAlbumId, musicSong.getAlbumId()).one();
            albumVo = this.convertMapper.toAlbumVo(album);
        }
        songVo.setSingerList(singerVos);
        songVo.setAlbum(albumVo);
        return songVo;
    }
}




