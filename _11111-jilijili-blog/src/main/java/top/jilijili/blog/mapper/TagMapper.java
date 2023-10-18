package top.jilijili.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.pojo.entity.blog.Tag;

/**
* @author admin
* @description 针对表【blog_tag(博客标签)】的数据库操作Mapper
* @createDate 2023-08-13 23:19:57
* @Entity top.jilijili.blog.entity.Tag
*/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}




