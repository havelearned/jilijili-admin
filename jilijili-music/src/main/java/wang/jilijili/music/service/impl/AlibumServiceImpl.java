package wang.jilijili.music.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.music.mapper.AlibumMapper;
import wang.jilijili.music.pojo.bo.AlibumConvertBo;
import wang.jilijili.music.pojo.dto.AlibumDto;
import wang.jilijili.music.pojo.entity.Alibum;
import wang.jilijili.music.service.AlibumService;

/**
 * @author admin
 * @description 针对表【alibum(专辑表)】的数据库操作Service实现
 * @createDate 2023-03-21 15:15:44
 */
@Service
public class AlibumServiceImpl extends ServiceImpl<AlibumMapper, Alibum>
        implements AlibumService {

    AlibumMapper alibumMapper;
    AlibumConvertBo alibumConvertBo;

    public AlibumServiceImpl(AlibumMapper alibumMapper, AlibumConvertBo alibumConvertBo) {
        this.alibumMapper = alibumMapper;
        this.alibumConvertBo = alibumConvertBo;
    }

    @Override
    @DS("slave_1")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public AlibumDto create(AlibumDto alibumDto) {
        Alibum alibum = this.alibumConvertBo.toAlibum(alibumDto);
        alibum.setId(KsuidGenerator.generate());
        int insert = this.alibumMapper.insert(alibum);
        if (insert >= 1) {

            return this.alibumConvertBo.toAlibumDto(this.getById(alibum.getId()));
        }

        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    @Override
    @DS("slave_1")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public AlibumDto update(AlibumDto alibumDto) {
        Alibum alibum = this.alibumConvertBo.toAlibum(alibumDto);
        boolean update = this.updateById(alibum);
        if (update) {

            return this.alibumConvertBo.toAlibumDto(this.getById(alibum.getId()));
        }

        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);

    }
}




