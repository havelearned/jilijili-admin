package wang.jilijili.common.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import wang.jilijili.common.core.pojo.dto.SysDictTypeDto;
import wang.jilijili.common.core.pojo.entity.SysDictType;

/**
* @author admin
* @description 针对表【sys_dict_type(字典类型表)】的数据库操作Service
* @createDate 2023-04-29 11:18:36
*/
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 插入字典类型
     * @param sysDictTypeDto dto
     * @return if create success return true
     */
    Boolean create(SysDictTypeDto sysDictTypeDto);

    /**
     * 更新字典类型
     * @param sysDictTypeDto dto
     * @return if update success return true
     */
    Boolean update(SysDictTypeDto sysDictTypeDto);
}
