package top.jilijili.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.pojo.entity.blog.Category;

/**
* @author admin
* @description 针对表【blog_category(文章分类)】的数据库操作Mapper
* @createDate 2023-08-13 23:19:57
* @Entity top.jilijili.blog.entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




