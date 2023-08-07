package top.jilijili.common.core.pojo.bo;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.common.core.pojo.dto.SysDictDataDto;
import top.jilijili.common.core.pojo.dto.SysDictTypeDto;
import top.jilijili.common.core.pojo.entity.SysDictData;
import top.jilijili.common.core.pojo.entity.SysDictType;

/**
 * @author admin
 */
@Mapper(componentModel = "spring")
@Component
public interface SysDictCovertBo {


    /**
     * 字典类型转entity
     * @param sysDictTypeDto dto
     * @return SysDictType 实体类
     */
    SysDictType toSysDictType(SysDictTypeDto sysDictTypeDto);

    /**
     * 字典数据转实体类
     * @param sysDictDataDto dto
     * @return SysDictDataDto entity
     */
    SysDictData toSysDictData(SysDictDataDto sysDictDataDto);
}
