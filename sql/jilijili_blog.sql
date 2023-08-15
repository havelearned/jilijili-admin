DROP TABLE IF EXISTS `blog_tag`;
create table blog_tag
(
    tag_id         bigint(32)  not null primary key comment '标签id',
    child_id       bigint(32)  not null default 0 comment '多级标签',
    `order`        tinyint     not null default 99 comment '排序',
    tag_title      varchar(32) not null default '' comment '标签名称',
    `created_time` datetime    not null  NULL COMMENT '创建时间',
    `updated_time` datetime    not null  NULL COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='博客标签';
insert into blog_tag(tag_id, child_id, `order`, tag_title, created_time, updated_time)
values (1, 0, 99, 'java入门1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 0, 99, 'java入门2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 1, 99, 'java入门1-1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 1, 99, 'java入门1-2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 2, 99, 'java入门2-1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


DROP TABLE IF EXISTS blog_category;

CREATE TABLE blog_category
(
    category_id  bigint(32)  NOT NULL PRIMARY KEY COMMENT '分类id',
    child_id     bigint(32)  NOT NULL DEFAULT 0 COMMENT '多级分类',
    title        varchar(32) NOT NULL DEFAULT '' COMMENT '分类名称',
    `order`      tinyint     NOT NULL DEFAULT 99 COMMENT '排序',
    created_time datetime              NULL COMMENT '创建时间',
    updated_time datetime              NULL COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 -- 修改字符集为utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='文章分类';

INSERT INTO blog_category(category_id, child_id, title, `order`, created_time, updated_time)
VALUES (1, 0, '后端开发', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 1, 'java', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 1, 'C++', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 1, 'go', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 0, '前端技术', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (6, 5, 'vue', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


DROP TABLE IF EXISTS blog_article;

CREATE TABLE blog_article
(
    article_id    bigint(32)    NOT NULL PRIMARY KEY COMMENT '文章id',
    user_id       bigint(32)    NOT NULL COMMENT '用户id',
    picture       varchar(1024) NOT NULL DEFAULT '' COMMENT '文章顶部图像',
    title         varchar(32)   NOT NULL DEFAULT '' COMMENT '文章标题',
    content       text COMMENT '文章内容',
    view_count    int           NOT NULL DEFAULT 0 COMMENT '浏览量',
    comment_count int           NOT NULL DEFAULT 0 COMMENT '评论量',
    created_time  datetime                NULL COMMENT '创建时间',
    updated_time  datetime                NULL COMMENT '更新时间'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 -- 修改字符集为utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='博客文章';

INSERT INTO blog_article(article_id, user_id, picture, title, content, view_count, comment_count, created_time,
                         updated_time)
VALUES (1, 1, 'https://www.dmoe.cc/random.php', '文章标题1', '# 标题1   ## 标题2  ```java public class Main(){} ```', 1,
        1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 1, 'https://www.dmoe.cc/random.php', '文章标题2', '# 标题1   ## 标题2  ```java public class Main(){} ```', 1,
        1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 1, 'https://www.dmoe.cc/random.php', '文章标题3', '# 标题1   ## 标题2  ```java public class Main(){} ```', 1,
        1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 1, 'https://www.dmoe.cc/random.php', '文章标题4', '# 标题1   ## 标题2  ```java public class Main(){} ```', 1,
        1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 1, 'https://www.dmoe.cc/random.php', '文章标题5', '# 标题1   ## 标题2  ```java public class Main(){} ```', 1,
        1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


DROP TABLE IF EXISTS blog_comment;

CREATE TABLE blog_comment
(
    comment_id   bigint(32)   NOT NULL PRIMARY KEY COMMENT '评论id',
    child_id     bigint(32)   NOT NULL DEFAULT 0 COMMENT '多级评论',
    article_id   bigint(32)   NOT NULL DEFAULT 0 COMMENT '被评论的博客id',
    user_id      bigint(32)   NOT NULL COMMENT '用户id',
    content      varchar(512) NOT NULL DEFAULT '' COMMENT '评论内容',
    created_time datetime               NULL COMMENT '创建时间',
    updated_time datetime               NULL COMMENT '更新时间'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 -- 支持表情包
  ROW_FORMAT = DYNAMIC COMMENT ='文章评论表';

INSERT INTO blog_comment(comment_id, child_id, article_id, user_id, content, created_time, updated_time)
VALUES (1, 0, 1, 1, '文章真不错1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 1, 1, 1, '文章真不错2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 1, 1, 1, '文章真不错2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 2, 1, 1, '文章真不错3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 2, 2, 1, '文章真不错3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (6, 3, 1, 1, '文章真不错4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


drop table if exists blog_tag_category_article;
create table blog_tag_category_article
(
    id          bigint(32) not null primary key comment 'id',
    article_id  bigint(32) NOT NULL COMMENT '文章id',
    category_id bigint(32) COMMENT '分类id',
    tag_id      bigint(32) comment '标签id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 -- 支持表情包
  ROW_FORMAT = DYNAMIC COMMENT ='分类,标签和文章关联表';
insert into blog_tag_category_article(id, article_id, category_id, tag_id)
VALUES (1, 1, 1, 1),
       (2, 1, 1, 2),
       (3, 1, 1, 3),
       (4, 1, 1, 4),
       (5, 2, 1, 1),
       (6, 2, 1, 2),
       (7, 2, 1, 3),
       (8, 3, 2, 1);