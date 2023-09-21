package top.jilijili.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.module.entity.Category;
import top.jilijili.blog.service.CategoryService;
import top.jilijili.blog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【blog_category(文章分类)】的数据库操作Service实现
* @createDate 2023-08-13 23:19:57
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




