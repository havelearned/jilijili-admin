package wang.jilijili.music.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import wang.jilijili.music.pojo.entity.Music;

/**
 * @author admin
 * @description 针对表【music】的数据库操作Mapper
 * @createDate 2023-03-09 10:14:58
 * @Entity wang.jilijili.music.pojo.entity.Music
 */
@DS("slave_1")
@Repository
public interface MusicMapper extends BaseMapper<Music> {

}




