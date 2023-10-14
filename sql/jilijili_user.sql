-- 字典表
-- auto-generated definition
create table sys_dict
(
    dictionary_id         bigint auto_increment
        primary key,
    dictionary_type       varchar(255)                       not null comment '字段类型',
    dictionary_code       varchar(255)                       not null comment '字段代码',
    dictionary_item_name  varchar(255)                       not null comment '字段项名称',
    dictionary_item_value varchar(255)                       null comment '字段项值',
    dictionary_item_order int                                null comment '排序',
    status                tinyint  default 1                 not null comment '状态:1开启 0停用',
    created_time          datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_time          datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
    comment '字典表' row_format = DYNAMIC;

create index idx_DictionaryType on sys_dict (dictionary_type);


drop table if exists sys_dict_item;
-- 字典表
-- auto-generated definition
create table sys_dict_item
(
    dict_item_id    bigint auto_increment primary key,
    dictionary_type varchar(255)      not null comment '字段类型',
    item_label      varchar(255)      null comment '字段项名称',
    item_value      varchar(255)      null comment '字段项值',
    item_order      int               null comment '排序',
    `status`        tinyint default 1 not null comment '状态:1开启 0停用'
) comment '字典item表' row_format = DYNAMIC;

drop table if exists sys_notify;
create table sys_notify
(
    notify_id      bigint primary key auto_increment comment '通知id，唯一标识符',
    sender_id      bigint      not null comment '发送通知的用户或系统组件的标识符',
    receiver_id    bigint      not null comment '接收通知的用户或系统组件的标识符',
    notify_type    varchar(50) not null comment '通知类型，例如警报、错误、新用户注册,全体通告等',
    notify_content text        not null comment '通知的内容，可以是文本、html、json等形式的消息',
    notify_status  tinyint default 0 comment '通知的状态，例如已读(1)、未读(0)、已处理等',
    created_time   datetime comment '通知创建时间',
    updated_time   datetime comment '通知更新时间'
);

