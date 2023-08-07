package top.jilijili.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.system.entity.MusicAlbum;
import top.jilijili.system.entity.dto.MusicAlbumDto;
import top.jilijili.system.entity.vo.MusicAlbumVo;

import java.util.List;

/**
* @author admin
* @description 针对表【music_album(专辑表)】的数据库操作Service
* @createDate 2023-07-15 07:02:33
*/
public interface MusicAlbumService extends IService<MusicAlbum> {

    Boolean saveAlbum(MusicAlbumDto musicAlbumDto);

    Boolean updateAlbum(MusicAlbumDto musicAlbumDto);

    Boolean removeAlbum(List<Long> idList);


    IPage<MusicAlbumVo> pageSelectAlbum(MusicAlbumDto musicAlbumDto);
}
