drop table if exists role;
CREATE TABLE role
(
    id           VARCHAR(32)  NOT NULL PRIMARY KEY COMMENT '角色ID',
    `name`       VARCHAR(128) NULL COMMENT '角色名称',
    title        VARCHAR(128) NULL COMMENT '角色标识',
    created_time datetime(6)  NULL COMMENT '创建时间',
    updated_time datetime(6)  NULL COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT '角色表';

insert into role(id, name, title, created_time, updated_time)
    value (1, 'ROLE_ADMIN', '普通用户', '2023-03-07 20:40:09', '2023-03-07 20:40:09');
insert into role(id, name, title, created_time, updated_time)
    value (2, 'ROLE_SUPER_ADMIN', '超级管理员', '2023-03-07 20:40:09', '2023-03-07 20:40:09');
