package wang.jilijili.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.jilijili.music.pojo.entity.Music;
import wang.jilijili.music.pojo.vo.MusicDetailVo;

/**
 * @author admin
 * @description 针对表【music(歌词表)】的数据库操作Mapper
 * @createDate 2023-03-27 11:04:06
 * @Entity wang.jilijili.music.pojo.entity.Music
 */

@Mapper
public interface MusicMapper extends BaseMapper<Music> {


    /**
     * 通过歌曲id查询歌曲歌手专辑信息
     *
     * @param musicId 音乐id
     * @return
     */
    MusicDetailVo queryMusicInfoById(@Param("musicId") String musicId);
}




