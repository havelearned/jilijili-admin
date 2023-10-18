package wang.jilijili.musics.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.module.pojo.entity.music.MusicAlbum;
import top.jilijili.module.pojo.entity.music.MusicSinger;
import top.jilijili.module.pojo.entity.music.MusicSong;
import top.jilijili.module.pojo.dto.music.MusicAlbumDto;
import top.jilijili.module.pojo.dto.music.MusicSingerDto;
import top.jilijili.module.pojo.dto.music.MusicSongDto;
import top.jilijili.module.pojo.vo.music.MusicAlbumVo;
import top.jilijili.module.pojo.vo.music.MusicSingerVo;
import top.jilijili.module.pojo.vo.music.MusicSongVo;


/**
 * @author Amani
 * @date 2023年06月22日 13:41
 */
@Mapper(componentModel = "spring")
@Component
public interface ConvertMapper {


    /*---------------music singer------------------*/
    MusicSinger toSingerEntity(MusicSingerDto musicSingerDto);

    MusicSingerVo toSingerVo(MusicSinger musicSinger);

    MusicSingerDto toSingerDto(MusicSinger musicSinger);

    /*---------------music song------------------*/
    MusicSong toSongEntity(MusicSongDto musicSongDto);

    MusicSongVo toSongVo(MusicSong musicSong);

    /*---------------music album------------------*/
    MusicAlbum toAlbumEntity(MusicAlbumDto musicAlbumDto);

    MusicAlbumVo toAlbumVo(MusicAlbum musicAlbum);


}
