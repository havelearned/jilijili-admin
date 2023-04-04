package wang.jilijili.common.core.pojo.bo;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import wang.jilijili.common.core.pojo.dto.FileManageDto;
import wang.jilijili.common.core.pojo.entity.FileManage;

/**
 * @author Amani
 * @date 2023年04月04日 10:42
 */
@Mapper(componentModel = "spring")
@Component
public interface FileManageConvertBo {


    /**
     * 转dto
     *
     * @param fileManage 实体类
     * @return wang.jilijili.common.core.pojo.dto.FileManageDto
     * @author Amani
     * @date 2023/4/4 10:44
     */
    FileManageDto toFileManageDto(FileManage fileManage);


    /**
     * 转entity
     *
     * @param fileManageDto dto
     * @return wang.jilijili.common.core.pojo.entity.FileManage
     * @author Amani
     * @date 2023/4/4 10:45
     */
    FileManage toFileManageEntity(FileManageDto fileManageDto);
}
