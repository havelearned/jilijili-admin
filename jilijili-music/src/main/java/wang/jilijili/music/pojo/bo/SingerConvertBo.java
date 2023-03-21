package wang.jilijili.music.pojo.bo;

import org.mapstruct.Mapper;

import wang.jilijili.music.pojo.dto.SingerDto;
import wang.jilijili.music.pojo.entity.Singer;
import wang.jilijili.music.pojo.vo.SingerVo;

/**
 * @author Amani
 * @date 2023年03月21日 13:36
 */
@Mapper(componentModel = "spring")
public interface SingerConvertBo {

    /**
     * 转entity
     *
     * @param singerDto 转entity
     * @return wang.jilijili.music.pojo.entity.Singer
     * @author Amani
     * @date 2023/3/21 13:40
     */
    Singer toSinger(SingerDto singerDto);

    /**
     * 转vo
     *
     * @param singerDto 转vo
     * @return wang.jilijili.music.pojo.vo.SingerVo
     * @author Amani
     * @date 2023/3/21 13:40
     */
    SingerVo toSingerVo(SingerDto singerDto);


    /**
     * 转dto
     *
     * @param singer 转dto
     * @return wang.jilijili.music.pojo.dto.SingerDto
     * @author Amani
     * @date 2023/3/21 13:40
     */
    SingerDto toSingerDto(Singer singer);


}
