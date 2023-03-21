package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.music.mapper.SingerMapper;
import wang.jilijili.music.pojo.bo.SingerConvertBo;
import wang.jilijili.music.pojo.dto.SingerDto;
import wang.jilijili.music.pojo.entity.Singer;
import wang.jilijili.music.service.SingerService;

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
    public SingerDto create(SingerDto singerDto) {
        Singer singer = this.singerConvertBo.toSinger(singerDto);
        boolean save = this.save(singer);
        if (save) {
            return this.singerConvertBo.toSingerDto(singer);
        }

        throw new BizException(ExceptionType.BAD_REQUEST);
    }

    @Override
    public SingerDto update(SingerDto singerDto) {
        Singer singer = this.singerConvertBo.toSinger(singerDto);
        if (this.updateById(singer)) {
            return this.singerConvertBo.toSingerDto(singer);
        }

        throw new BizException(ExceptionType.BAD_REQUEST);
    }
}




