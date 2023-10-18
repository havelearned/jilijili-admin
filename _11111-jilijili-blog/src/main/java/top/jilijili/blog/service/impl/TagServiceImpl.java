package top.jilijili.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.module.pojo.entity.blog.Tag;
import top.jilijili.blog.service.TagService;
import top.jilijili.blog.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【blog_tag(博客标签)】的数据库操作Service实现
* @createDate 2023-08-13 23:19:57
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




