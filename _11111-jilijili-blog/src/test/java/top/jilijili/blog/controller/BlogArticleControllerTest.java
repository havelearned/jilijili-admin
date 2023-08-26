package top.jilijili.blog.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.jilijili.blog.mapper.ArticleMapper;
import top.jilijili.blog.mapper.ConvertMapper;
import top.jilijili.blog.service.*;

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
    public void _01() {


    }

}