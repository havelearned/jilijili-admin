package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import wang.jilijili.music.pojo.entity.SingerAlibum;
import wang.jilijili.music.pojo.request.AlibumCreateRequest;
import wang.jilijili.music.pojo.vo.SingerVo;
import wang.jilijili.music.service.AlibumService;
import wang.jilijili.music.service.SingerAlibumService;

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

    SingerAlibumService singerAlibumService;


    public AlibumServiceImpl(AlibumMapper alibumMapper, AlibumConvertBo alibumConvertBo, SingerAlibumService singerAlibumService) {
        this.alibumMapper = alibumMapper;
        this.alibumConvertBo = alibumConvertBo;
        this.singerAlibumService = singerAlibumService;
    }

    @Override
    public AlibumDto create(AlibumCreateRequest alibumCreateRequest) {
        Alibum alibum = this.alibumConvertBo.toAlibum(alibumCreateRequest);

        alibum.setId(KsuidGenerator.generate());

        int insert = this.alibumMapper.insert(alibum);

        List<SingerAlibum> singerAlibums = alibumCreateRequest.getSingerId()
                .stream().map(item -> new SingerAlibum(item.replace(",", ""), alibum.getId())).toList();

        boolean b = this.singerAlibumService.saveBatch(singerAlibums);
        if (insert > 0 && b) {
            return this.alibumConvertBo.toAlibumDto(this.getById(alibum.getId()));
        }
        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    @Override
    public AlibumDto udpated(AlibumCreateRequest alibumCreateRequest) {

        Alibum alibum = this.alibumConvertBo.toAlibum(alibumCreateRequest);
        int isUpdated = this.alibumMapper.updateById(alibum);

        // 删除已有关联专辑歌手
        this.singerAlibumService.remove(new LambdaQueryWrapper<SingerAlibum>().eq(SingerAlibum::getAlibumId, alibum.getId()));

        List<SingerAlibum> singerAlibums = alibumCreateRequest.getSingerId()
                .stream().map(item -> new SingerAlibum(item.replace(",", ""), alibum.getId())).toList();

        // 保存新的专辑歌手
        boolean isSave = this.singerAlibumService.saveBatch(singerAlibums);

        if (isUpdated > 0 && isSave) {
            return this.alibumConvertBo.toAlibumDto(alibum);
        }
        throw new BizException(ExceptionType.REQUEST_OPERATE_ERROR);
    }

    @Override
    public List<SingerVo> queryByAlibumId(String id) {
        return this.alibumMapper.queryByAlibumId(id);

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean deleteAlbumAndArtist(List<String> idList) {
        // 通过专辑id删除绑定的歌手
        boolean removed = this.singerAlibumService.remove(new LambdaQueryWrapper<SingerAlibum>().in(SingerAlibum::getAlibumId, idList));
        // TODO 删除专辑内的音乐列表
        // 删除专辑
        this.alibumMapper.deleteBatchIds(idList);

        return removed;
    }
}




