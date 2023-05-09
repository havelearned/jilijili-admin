package wang.jilijili.common.core.pojo.bo;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import wang.jilijili.common.core.pojo.dto.OperationLogDto;
import wang.jilijili.common.core.pojo.entity.OperationLog;

/**
 * @author Amani
 * @date 2023年04月04日 11:21
 */
@Mapper(componentModel = "spring")
@Component
public interface OperationLogConvertBo {

    /**
     * 转entity
     *
     * @param operationLogDto dto
     * @return wang.jilijili.common.core.pojo.entity.OperationLog
     * @author Amani
     * @date 2023/4/4 11:27
     */
    OperationLog toOperationLogEntity(OperationLogDto operationLogDto);


    /**
     * 转dto
     *
     * @param operationLog 实体类
     * @return wang.jilijili.common.core.pojo.dto.OperationLogDto
     * @author Amani
     * @date 2023/4/4 11:28
     */
    OperationLogDto toOperationLogDto(OperationLog operationLog);


}
