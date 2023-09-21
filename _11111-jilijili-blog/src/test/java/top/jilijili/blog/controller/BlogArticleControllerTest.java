package top.jilijili.blog.controller;

import com.alibaba.fastjson2.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.module.entity.vo.ArticleVo;
import top.jilijili.module.entity.vo.TagVo;
import top.jilijili.blog.mapper.ArticleMapper;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.*;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogArticleControllerTest {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CommentService commentService;

    @Autowired
    TagCategoryArticleService tagCategoryArticleService;

    @Autowired
    TagService tagService;

    @Autowired
    ConvertMapper convertMapper;

    @Test
    public void _02(){
        List<TagVo> tagVos = articleMapper.queryTagsByArticleId(1);
        System.out.println("==============================");
        System.out.println(JSON.toJSONString(tagVos));
        System.out.println("==============================");
    }

    @Test
    public void _01() {

        List<ArticleVo> articleVos = articleMapper.queryArticleByCategoryId(1, 2, 1);

        System.out.println("==============================");
        System.out.println(JSON.toJSONString(articleVos));
        System.out.println("==============================");


    }

}