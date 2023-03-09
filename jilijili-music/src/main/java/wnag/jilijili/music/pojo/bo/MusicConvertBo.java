package wnag.jilijili.music.pojo.bo;


import org.mapstruct.Mapper;
import wnag.jilijili.music.pojo.dto.MusicDto;
import wnag.jilijili.music.pojo.entity.Music;
import wnag.jilijili.music.pojo.request.MusicCreateRequest;
import wnag.jilijili.music.pojo.request.MusicUpdateRequest;
import wnag.jilijili.music.pojo.vo.MusicVo;

/**
 * @author Amani
 * @date 2023年03月09日 10:43
 */
@Mapper(componentModel = "spring")
public interface MusicConvertBo {

    /**
     * 转 Music
     *
     * @param request request
     * @return wnag.jilijili.music.pojo.entity.Music
     * @author Amani
     * @date 2023/3/9 10:47
     */
    Music toMusic(MusicCreateRequest request);

    /**
     *  转 Music
     * @author Amani
     * @date 2023/3/9 11:18
     * @param request  request
     * @return wnag.jilijili.music.pojo.entity.Music
     */
    Music toMusic(MusicUpdateRequest request);

    /**
     * 转 MusicVo
     *
     * @param dto dto
     * @return wnag.jilijili.music.pojo.vo.MusicVo
     * @author Amani
     * @date 2023/3/9 10:47
     */
    MusicVo toMusicVo(MusicDto dto);


    /**
     * 转 MusicDto
     *
     * @param music music
     * @return wnag.jilijili.music.pojo.dto.MusicDto
     * @author Amani
     * @date 2023/3/9 10:47
     */
    MusicDto toMusicDto(Music music);

}
