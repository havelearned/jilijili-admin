package top.jilijili.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.system.entity.MusicAlbumSinger;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【music_album_singer(专辑和歌手信息表)】的数据库操作Mapper
* @createDate 2023-07-18 14:13:43
* @Entity top.jilijili.system.pojo.entity.MusicAlbumSinger
*/
@Mapper
public interface MusicAlbumSingerMapper extends BaseMapper<MusicAlbumSinger> {

}




