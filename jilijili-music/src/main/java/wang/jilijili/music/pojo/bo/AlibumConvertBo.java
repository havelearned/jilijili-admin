package wang.jilijili.music.pojo.bo;

import org.mapstruct.Mapper;
import wang.jilijili.music.pojo.dto.AlibumDto;
import wang.jilijili.music.pojo.entity.Alibum;
import wang.jilijili.music.pojo.request.AlibumCreateRequest;
import wang.jilijili.music.pojo.vo.AlibumVo;

/**
 * @author Amani
 * @date 2023年03月21日 13:36
 */
@Mapper(componentModel = "spring")
public interface AlibumConvertBo {

    /**
     * 创建表单转实体类
     */
    Alibum toAlibum(AlibumCreateRequest alibumCreateRequest);

    /**
     * 转entity
     *
     * @param alibumDto 转entity
     * @return wang.jilijili.music.pojo.entity.AlibumDto
     * @author Amani
     * @date 2023/3/21 13:40
     */
    Alibum toAlibum(AlibumDto alibumDto);

    /**
     * 转vo
     *
     * @param alibumDto 转vo
     * @return wang.jilijili.music.pojo.vo.AlibumVo
     * @author Amani
     * @date 2023/3/21 13:40
     */
    AlibumVo toAlibumVo(AlibumDto alibumDto);


    /**
     * 转dto
     *
     * @param alibum 转dto
     * @return wang.jilijili.music.pojo.dto.Alibum
     * @author Amani
     * @date 2023/3/21 13:40
     */
    AlibumDto toAlibumDto(Alibum alibum);


}
