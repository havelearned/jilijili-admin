package top.jilijili.common.core.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.common.core.pojo.dto.SysDictTypeDto;
import top.jilijili.common.core.pojo.entity.SysDictType;
import top.jilijili.common.core.pojo.vo.DictTypeVO;

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

    /**
     * 查询后按DictTyp查询
     * @param dictType 字典类型
     * @return 字典数据
     */
    DictTypeVO queryByDict(String dictType);
}