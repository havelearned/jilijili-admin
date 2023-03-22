package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import org.springframework.stereotype.Service;
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

    private AlibumService alibumService;
    private AlibumConvertBo alibumConvertBo;

    public AlibumServiceImpl(AlibumService alibumService, AlibumConvertBo alibumConvertBo) {
        this.alibumService = alibumService;
        this.alibumConvertBo = alibumConvertBo;
    }

    @Override
    public AlibumDto create(AlibumDto alibumDto) {
        Alibum alibum = this.alibumConvertBo.toAlibum(alibumDto);
        alibum.setId(KsuidGenerator.generate());
        boolean update = this.save(alibum);
        if (update) {

            return this.alibumConvertBo.toAlibumDto(this.getById(alibum.getId()));
        }

        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    @Override
    public AlibumDto update(AlibumDto alibumDto) {
        Alibum alibum = this.alibumConvertBo.toAlibum(alibumDto);
        boolean update = this.updateById(alibum);
        if (update) {

            return this.alibumConvertBo.toAlibumDto(this.getById(alibum.getId()));
        }

        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);

    }
}




