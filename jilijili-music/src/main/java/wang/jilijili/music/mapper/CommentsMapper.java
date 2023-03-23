package wang.jilijili.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wang.jilijili.music.pojo.entity.Comments;

/**
 * @author admin
 * @description 针对表【comments】的数据库操作Mapper
 * @createDate 2023-03-23 15:00:22
 * @Entity wang.jilijili.music.pojo.entity.Comments
 */

@Mapper
public interface CommentsMapper extends BaseMapper<Comments> {

}




