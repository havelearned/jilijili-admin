package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import org.springframework.stereotype.Service;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.music.mapper.AlibumMapper;
import wang.jilijili.music.mapper.SingerAlibumMapper;
import wang.jilijili.music.pojo.bo.AlibumConvertBo;
import wang.jilijili.music.pojo.dto.AlibumDto;
import wang.jilijili.music.pojo.entity.Alibum;
import wang.jilijili.music.pojo.entity.SingerAlibum;
import wang.jilijili.music.pojo.request.AlibumCreateRequest;
import wang.jilijili.music.pojo.vo.SingerVo;
import wang.jilijili.music.service.AlibumService;

import java.util.List;

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
    SingerAlibumMapper singerAlibumMapper;

    public AlibumServiceImpl(AlibumMapper alibumMapper, AlibumConvertBo alibumConvertBo, SingerAlibumMapper singerAlibumMapper) {
        this.alibumMapper = alibumMapper;
        this.alibumConvertBo = alibumConvertBo;
        this.singerAlibumMapper = singerAlibumMapper;
    }

    @Override
    public AlibumDto create(AlibumCreateRequest alibumCreateRequest) {
        Alibum alibum = this.alibumConvertBo.toAlibum(alibumCreateRequest);

        alibum.setId(KsuidGenerator.generate());

        if (this.alibumMapper.insert(alibum) >= 1 &&
                this.alibumMapper.saveBySingerIdByAlibumId(alibumCreateRequest.getSingerId(), alibum.getId()) >= 1) {

            return this.alibumConvertBo.toAlibumDto(this.getById(alibum.getId()));
        }

        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    @Override
    public AlibumDto udpated(AlibumCreateRequest alibumCreateRequest) {

        Alibum alibum = this.alibumConvertBo.toAlibum(alibumCreateRequest);
        this.alibumMapper.updateById(alibum);

        SingerAlibum singerAlibum = new SingerAlibum();
        singerAlibum.setAlibumId(alibum.getId());
        singerAlibum.setSingerId(alibumCreateRequest.getSingerId());

        this.singerAlibumMapper.update(singerAlibum, new UpdateWrapper<>());
        return this.alibumConvertBo.toAlibumDto(alibum);
    }

    @Override
    public List<SingerVo> queryByAlibumId(String id) {
        return this.alibumMapper.queryByAlibumId(id);

    }
}




