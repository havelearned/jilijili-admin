package wang.jilijili.music.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.jilijili.music.pojo.entity.Singer;
import wang.jilijili.music.pojo.vo.SingerInfoVo;

/**
 * @author admin
 * @description 针对表【singer(歌手表)】的数据库操作Mapper
 * @createDate 2023-03-20 22:54:49
 * @Entity wang.jilijili.music.pojo.entity.Singer
 */
@DS("slave_1")
@Mapper
public interface SingerMapper extends BaseMapper<Singer> {

    /**
     * 通过歌手id查询所有信息
     *
     * @param singerId 歌手id
     * @return wang.jilijili.music.pojo.vo.SingerInfoVo
     * @author Amani
     * @date 2023/3/26 9:29
     */
    SingerInfoVo getSingerAlbumSongInformation(@Param("singerId") String singerId);

}




