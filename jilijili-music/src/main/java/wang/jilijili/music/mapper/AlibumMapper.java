package wang.jilijili.music.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.jilijili.music.pojo.entity.Alibum;
import wang.jilijili.music.pojo.vo.SingerVo;

import java.util.List;

/**
* @author admin
* @description 针对表【alibum(专辑表)】的数据库操作Mapper
* @createDate 2023-03-21 15:15:44
* @Entity wang.jilijili.music.pojo.entity.Alibum
*/

@DS("slave_1")
@Mapper
public interface AlibumMapper extends BaseMapper<Alibum> {

    /**
     * 保存歌手和专辑中间表信息
     * @param singerId 歌手id
     * @param id 专辑id
     * @return
     */
    int saveBySingerIdByAlibumId(@Param(value = "singerId") String singerId,@Param(value = "id") String id);

    /**
     * 通过专辑id查询歌手列表
     * @param alibumId
     * @return
     */
    List<SingerVo> queryByAlibumId(@Param("alibumId") String alibumId);
}




