package top.jilijili.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.jilijili.module.pojo.entity.music.MusicAlbum;
import top.jilijili.module.pojo.vo.music.MusicAlbumVo;

/**
 * @author admin
 * @description 针对表【music_album(专辑表)】的数据库操作Mapper
 * @createDate 2023-07-15 07:02:33
 * @Entity top.jilijili.system.pojo.entity.MusicAlbum
 */
@Mapper
public interface MusicAlbumMapper extends BaseMapper<MusicAlbum> {

    IPage<MusicAlbumVo> queryAlbumAndSingerPage(IPage<MusicAlbum> page, @Param(Constants.WRAPPER) Wrapper<MusicAlbum> queryWrapper);

}




