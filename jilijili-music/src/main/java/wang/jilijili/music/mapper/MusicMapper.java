package wang.jilijili.music.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.jilijili.music.pojo.entity.Music;

/**
 * @author admin
 * @description 针对表【music】的数据库操作Mapper
 * @createDate 2023-03-09 10:14:58
 * @Entity wang.jilijili.music.pojo.entity.Music
 */
@DS("slave_1")
@Mapper
public interface MusicMapper extends BaseMapper<Music> {

    IPage<Music> queryAllMusicInformation(IPage<Music> page, @Param(Constants.WRAPPER) Wrapper<Music> wrapper);

}




