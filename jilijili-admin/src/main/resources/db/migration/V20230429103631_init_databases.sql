create database  `jilijili-music` character set  utf8;
create database  `jilijili-user` character set  utf8;

create table if not exists `jilijili-music`.alibum
(
    id           varchar(32) not null
        primary key,
    album_name   varchar(32) null comment '专辑名称',
    details      text        null comment '专辑介绍',
    album_img    text        null comment '专辑封面',
    created_time datetime    null comment '发布时间',
    updated_time datetime    null
)
    comment '专辑表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.comments
(
    id           varchar(32)             not null
        primary key,
    children     varchar(32) default '0' null comment '树形id',
    details      varchar(255)            null comment '评论内容',
    targetid     varchar(32)             null comment '目标id，可能是歌单id、专辑id、歌曲id',
    type         tinyint                 null comment '评论类型 1歌单2专辑3歌曲',
    created_time datetime                null comment '评论时间',
    updated_time datetime                null
)
    comment '评论表' row_format = DYNAMIC;

create table if not exists `jilijili-user`.file_manage
(
    id           varchar(32)  not null
        primary key,
    filename     varchar(255) null comment '文件名称(不包含后缀)',
    filepath     varchar(255) null comment '文件全路径url',
    type         varchar(32)  null comment '文件类型: .png  .jpg',
    filesize     bigint       null comment '文件大小 字节',
    locked       tinyint      null comment '是否锁定',
    updated_time datetime     null,
    created_time datetime     null,
    access_time  datetime     null comment '访问时间'
)
    comment '文件管理表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.music
(
    id             varchar(32) not null
        primary key,
    name           varchar(32) null comment '歌曲名称',
    status         tinyint     null comment '歌曲状态::DRAFT 草稿, PUBLISHED 上架, CLOSED 下架',
    album_id       varchar(32) null comment '专辑id',
    description    text        null comment '歌曲简介',
    music_filepath text        null comment '歌曲路径',
    created_time   datetime    null,
    updated_time   datetime    null
)
    comment '歌曲表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.music_palylist
(
    id          int         not null
        primary key,
    music_id    varchar(32) null comment '歌曲id',
    playlist_id varchar(32) null comment '歌单id'
)
    comment '歌单_歌曲中间表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.music_user
(
    id       varchar(32) not null
        primary key,
    user_id  varchar(32) null,
    music_id varchar(32) null
)
    comment '用户_收藏歌曲中间表' row_format = DYNAMIC;

create table if not exists `jilijili-user`.operation_log
(
    id               varchar(32)  not null
        primary key,
    user_id          varchar(32)  null comment '用户id',
    username         varchar(64)  null comment '用户名称',
    last_login_ip    varchar(255) null comment '登录ip',
    operation_type   tinyint      null comment '操作类型: 1:添加 2:修改 3:删除 4:查询',
    content          text         null comment '操作内容',
    request_url      varchar(512) null comment '请求路径',
    method_execution varchar(512) null comment '包名+类名+方法名',
    request_data     text         null comment '传参的数据',
    module_name      varchar(32)  null comment '模块名称',
    created_time     datetime     null,
    updated_time     datetime     null
)
    comment '日志记录表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.playlist
(
    id           varchar(32)  not null
        primary key,
    title        varchar(32)  null comment '标题',
    image        text         null comment '图片地址',
    user_id      varchar(32)  null comment '创建者id',
    details      varchar(255) null comment '歌单介绍',
    type         tinyint      null comment '歌单类型',
    playlistnum  int          null comment '歌单点击量',
    created_time datetime     null,
    updated_time datetime     null
)
    comment '歌单表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.playlist_user
(
    id          varchar(32) not null
        primary key,
    playlist_id varchar(32) null comment '歌单id',
    user_id     varchar(32) null comment '用户id'
)
    comment '用户_收藏歌单的中间表' row_format = DYNAMIC;

create table if not exists `jilijili-user`.role
(
    id           varchar(32)  not null comment '角色ID'
        primary key,
    name         varchar(128) null comment '角色名称',
    title        varchar(128) null comment '角色标识',
    created_time datetime(6)  null comment '创建时间',
    updated_time datetime(6)  null comment '更新时间'
)
    comment '角色表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.singer
(
    id             varchar(32)       not null comment '歌手id'
        primary key,
    singer_name    varchar(32)       null comment '歌手名称',
    singer_details text              null comment '歌手简介',
    singer_photo   text              null comment '歌手头像',
    locked         tinyint default 0 null comment '是否禁用',
    created_time   datetime          null,
    updated_time   datetime          null
)
    comment '歌手表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.singer_alibum
(
    singer_id varchar(32) null comment '歌手id',
    alibum_id varchar(32) null comment '专辑id'
)
    comment '歌手和专辑信息表' row_format = DYNAMIC;

create table if not exists `jilijili-music`.singer_music
(
    id        bigint(32) auto_increment comment 'id'
        primary key,
    singer_id varchar(32) null comment '歌手id',
    music_id  varchar(32) null comment '歌曲id'
)
    comment '歌手和歌曲的中间表' row_format = DYNAMIC;

create table if not exists `jilijili-user`.sys_dict_data
(
    dict_code    bigint auto_increment comment '字典编码'
        primary key,
    dict_sort    int(4)       default 0   null comment '字典排序',
    dict_label   varchar(100) default ''  null comment '字典标签',
    dict_value   varchar(100) default ''  null comment '字典键值',
    dict_type    varchar(100) default ''  null comment '字典类型',
    status       char         default '0' null comment '状态（0正常 1停用）',
    created_time datetime                 null comment '创建时间',
    updated_time datetime                 null comment '更新时间',
    remark       varchar(500)             null comment '备注'
)
    comment '字典数据表' collate = utf8_bin;

create table if not exists `jilijili-user`.sys_dict_type
(
    dict_id      bigint auto_increment comment '字典主键'
        primary key,
    dict_name    varchar(100) default '' null comment '字典名称',
    dict_type    varchar(100) default '' null comment '字典类型',
    created_time datetime                null comment '创建时间',
    updated_time datetime                null comment '更新时间',
    remark       varchar(500)            null comment '备注',
    constraint dict_type
        unique (dict_type)
)
    comment '字典类型表' collate = utf8_bin;

create table if not exists `jilijili-user`.user
(
    id              varchar(32)          not null comment '用户ID'
        primary key,
    username        varchar(64)          not null comment '用户名',
    nickname        varchar(64)          null comment '用户昵称',
    password        varchar(64)          null comment '加密后的密码',
    gender          varchar(255)         null comment '性别',
    locked          tinyint(1) default 0 null comment '是否锁定,1-是,0-否',
    enabled         tinyint(1) default 1 null comment '是否可用,1-是,0-否',
    last_login_ip   varchar(64)          null comment '最后登录IP',
    last_login_time datetime(6)          null comment '最后登录IP',
    created_time    datetime(6)          null comment '创建时间',
    updated_time    datetime(6)          null comment '更新时间',
    constraint uk_user_username
        unique (username)
)
    comment '用户表' row_format = DYNAMIC;

create table if not exists `jilijili-user`.user_role
(
    user_id varchar(32) not null comment '用户ID',
    role_id varchar(32) not null comment '角色ID'
)
    comment '用户_角色中间表' row_format = DYNAMIC;

