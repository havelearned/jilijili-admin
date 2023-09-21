package wang.jilijili.musics.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.MusicAlbum;
import top.jilijili.module.entity.MusicSinger;
import top.jilijili.module.entity.MusicSong;
import top.jilijili.module.entity.dto.MusicAlbumDto;
import top.jilijili.module.entity.dto.MusicSingerDto;
import top.jilijili.module.entity.dto.MusicSongDto;
import top.jilijili.module.entity.vo.MusicAlbumVo;
import top.jilijili.module.entity.vo.MusicSingerVo;
import top.jilijili.module.entity.vo.MusicSongVo;


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
