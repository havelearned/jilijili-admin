package wang.jilijili.music.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.ksuid.KsuidGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.music.mapper.SingerMapper;
import wang.jilijili.music.pojo.bo.SingerConvertBo;
import wang.jilijili.music.pojo.dto.SingerDto;
import wang.jilijili.music.pojo.entity.Singer;
import wang.jilijili.music.pojo.vo.AlibumSongVo;
import wang.jilijili.music.pojo.vo.SingerInfoVo;
import wang.jilijili.music.service.SingerService;

import java.util.List;

/**
 * @author admin
 * @description 针对表【singer(歌手表)】的数据库操作Service实现
 * @createDate 2023-03-20 22:54:49
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer>
        implements SingerService {

    SingerMapper singerMapper;

    SingerConvertBo singerConvertBo;

    public SingerServiceImpl(SingerMapper singerMapper, SingerConvertBo singerConvertBo) {
        this.singerMapper = singerMapper;
        this.singerConvertBo = singerConvertBo;
    }

    @Override
    @DS("slave_1")
    
    public SingerDto create(SingerDto singerDto) {
        Singer singer = this.singerConvertBo.toSinger(singerDto);
        singer.setId(KsuidGenerator.generate());
        boolean save = this.save(singer);
        if (save) {
            return this.singerConvertBo.toSingerDto(singer);
        }

        throw new BizException(ExceptionType.BAD_REQUEST);
    }

    @Override
    @DS("slave_1")
    
    public SingerDto update(SingerDto singerDto) {
        Singer singer = this.singerConvertBo.toSinger(singerDto);
        boolean update = this.updateById(singer);
        if (update) {

            return this.singerConvertBo.toSingerDto(this.getById(singer.getId()));
        }

        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    @Override
    @DS("slave_1")
    
    public boolean deleteBatch(List<String> idList) {
        return this.singerMapper.deleteBatchIds(idList) > 0;
    }

    @Override
    public SingerInfoVo getSingerInfo(String singerId) {
        SingerInfoVo singerAlbumInformation = this.singerMapper.getSingerAlbumInformation(singerId);
        return singerAlbumInformation;
    }

    @Override
    public AlibumSongVo getSingerByAlibumBySongAll(String alibumId) {
        return this.singerMapper.getSingerByAlibumBySongAll(alibumId);
    }
}




