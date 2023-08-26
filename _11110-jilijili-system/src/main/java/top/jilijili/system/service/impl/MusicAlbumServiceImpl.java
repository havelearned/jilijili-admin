package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.jilijili.system.entity.MusicAlbum;
import top.jilijili.system.entity.MusicAlbumSinger;
import top.jilijili.system.entity.dto.MusicAlbumDto;
import top.jilijili.system.entity.vo.MusicAlbumVo;
import top.jilijili.system.mapper.ConvertMapper;
import top.jilijili.system.mapper.MusicAlbumMapper;
import top.jilijili.system.service.MusicAlbumService;
import top.jilijili.system.service.MusicAlbumSingerService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【music_album(专辑表)】的数据库操作Service实现
 * @createDate 2023-07-15 07:02:33
 */
@Service
@AllArgsConstructor
public class MusicAlbumServiceImpl extends ServiceImpl<MusicAlbumMapper, MusicAlbum>
        implements MusicAlbumService {

    private MusicAlbumSingerService musicAlbumSingerService;
    private MusicAlbumMapper musicAlbumMapper;
    private ConvertMapper convertMapper;

    @Override
    public Boolean saveAlbum(MusicAlbumDto musicAlbumDto) {
        MusicAlbum albumEntity = this.convertMapper.toAlbumEntity(musicAlbumDto);
        if (!StringUtils.hasText(albumEntity.getDesc())) {
            albumEntity.setDesc("暂无介绍");
        }
        boolean saved = this.save(albumEntity);
        if (!CollectionUtils.isEmpty(musicAlbumDto.getSingerIds()) && saved) {
            musicAlbumDto.setAlbumId(albumEntity.getAlbumId());

            List<MusicAlbumSinger> albumSingerList = musicAlbumDto.getSingerIds().stream()
                    .map(signerId -> new MusicAlbumSinger(albumEntity.getAlbumId(), signerId)).collect(Collectors.toList());
            saved = this.musicAlbumSingerService.saveBatch(albumSingerList);
        }
        return saved;
    }

    @Override
    public Boolean updateAlbum(MusicAlbumDto musicAlbumDto) {
        MusicAlbum albumEntity = this.convertMapper.toAlbumEntity(musicAlbumDto);
        boolean updated = this.updateById(albumEntity);
        if (!CollectionUtils.isEmpty(musicAlbumDto.getSingerIds()) && updated) {
            // 删除
            LambdaQueryWrapper<MusicAlbumSinger> qw = new LambdaQueryWrapper<>();
            qw.eq(MusicAlbumSinger::getAlbumId, albumEntity.getAlbumId());
            this.musicAlbumSingerService.remove(qw);

            // 重新添加
            List<MusicAlbumSinger> albumSingerList = musicAlbumDto.getSingerIds().stream()
                    .map(signerId -> new MusicAlbumSinger(albumEntity.getAlbumId(), signerId)).collect(Collectors.toList());

            updated = this.musicAlbumSingerService.saveBatch(albumSingerList);
        }
        return updated;
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Boolean removeAlbum(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) return false;
        boolean removed = this.removeBatchByIds(idList);
        if (removed) {
            LambdaQueryWrapper<MusicAlbumSinger> query = new LambdaQueryWrapper<>();
            query.in(MusicAlbumSinger::getAlbumId, idList);
            removed = this.musicAlbumSingerService.remove(query);
        }
        return removed;
    }

    @Override
    public IPage<MusicAlbumVo> pageSelectAlbum(MusicAlbumDto musicAlbumDto) {
        IPage<MusicAlbum> page = new Page<>(musicAlbumDto.getPage(), musicAlbumDto.getSize());
        MusicAlbum albumEntity = this.convertMapper.toAlbumEntity(musicAlbumDto);
        LambdaQueryWrapper<MusicAlbum> lambda = new QueryWrapper<MusicAlbum>().lambda();
        lambda.like(StringUtils.hasText(albumEntity.getAlbumName()), MusicAlbum::getAlbumName, albumEntity.getAlbumName())
                .orderBy(true, false, MusicAlbum::getCreatedTime);
        return this.musicAlbumMapper.queryAlbumAndSingerPage(page, lambda);
    }


}




