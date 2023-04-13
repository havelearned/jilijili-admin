package wang.jilijili.music.pojo.bo;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import wang.jilijili.music.pojo.dto.MusicDto;
import wang.jilijili.music.pojo.entity.Music;
import wang.jilijili.music.pojo.vo.MusicVo;


/**
 * 业务对象
 *
 * @author Amani
 * @date 2023年03月09日 10:43
 */
@Mapper(componentModel = "spring")
public interface MusicConvertBo {

    MusicConvertBo INSTANCE = Mappers.getMapper(MusicConvertBo.class);

    /**
     * 转 MusicVo
     *
     * @param dto dto
     * @return wnag.jilijili.music.pojo.vo.MusicVo
     * @author Amani
     * @date 2023/3/9 10:47
     */

    @Mapping(target = "status", expression = "java(wang.jilijili.music.enums.MusicStatus.fromValue(dto.getStatus()))")
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

    /**
     * 转entity
     *
     * @param musicDto
     * @return wang.jilijili.music.pojo.entity.Music
     * @author Amani
     * @date 27/3/2023 上午11:09
     */
    Music toMusic(MusicDto musicDto);
}
