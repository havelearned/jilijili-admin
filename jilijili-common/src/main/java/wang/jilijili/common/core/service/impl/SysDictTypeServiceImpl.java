package wang.jilijili.common.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.jilijili.common.core.mapper.SysDictDataMapper;
import wang.jilijili.common.core.mapper.SysDictTypeMapper;
import wang.jilijili.common.core.pojo.bo.SysDictCovertBo;
import wang.jilijili.common.core.pojo.dto.SysDictTypeDto;
import wang.jilijili.common.core.pojo.entity.SysDictData;
import wang.jilijili.common.core.pojo.entity.SysDictType;
import wang.jilijili.common.core.pojo.vo.DictTypeVO;
import wang.jilijili.common.core.service.SysDictTypeService;

/**
 * @author admin
 * @description 针对表【sys_dict_type(字典类型表)】的数据库操作Service实现
 * @createDate 2023-04-29 11:18:36
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType>
        implements SysDictTypeService {

    SysDictTypeMapper sysDictTypeMapper;
    SysDictDataMapper sysDictDataMapper;
    SysDictCovertBo sysDictCovertBo;

    @Autowired
    public SysDictTypeServiceImpl(SysDictTypeMapper sysDictTypeMapper, SysDictDataMapper sysDictDataMapper, SysDictCovertBo sysDictCovertBo) {
        this.sysDictTypeMapper = sysDictTypeMapper;
        this.sysDictDataMapper = sysDictDataMapper;
        this.sysDictCovertBo = sysDictCovertBo;
    }


    @Override
    public Boolean create(SysDictTypeDto sysDictTypeDto) {
        SysDictType sysDictType = this.sysDictCovertBo.toSysDictType(sysDictTypeDto);
        return this.sysDictTypeMapper.insert(sysDictType) > 0;
    }

    @Override
    public Boolean update(SysDictTypeDto sysDictTypeDto) {
        // 更新类型
        SysDictType sysDictType = this.sysDictCovertBo.toSysDictType(sysDictTypeDto);

        // 更新data
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictType(sysDictTypeDto.getDictType());

        return this.sysDictTypeMapper.updateById(sysDictType) > 0
                &&
                this.sysDictDataMapper.update(sysDictData, null) > 0;
    }

    @Override
    public DictTypeVO queryByDict(String dictType) {
        return this.sysDictTypeMapper.queryByDictTypeAfter(dictType);
    }
}




