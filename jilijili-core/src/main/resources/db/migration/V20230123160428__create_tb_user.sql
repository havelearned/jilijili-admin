CREATE TABLE user
(
    id              VARCHAR(32)          NOT NULL PRIMARY KEY COMMENT '用户ID',
    username        VARCHAR(64)          NOT NULL COMMENT '用户名',
    nickname        VARCHAR(64)          NULL COMMENT '用户昵称',
    password        VARCHAR(64)          NOT NULL COMMENT '加密后的密码',
    gender          VARCHAR(255)         NULL COMMENT '性别',
    locked          tinyint(1) default 0 NULL comment '是否锁定,1-是,0-否',
    enabled         tinyint(1) default 1 NULL Comment '是否可用,1-是,0-否',
    last_login_ip   VARCHAR(64)          NULL COMMENT '最后登录IP',
    last_login_time datetime(6)          NULL comment '最后登录IP',
    created_time    datetime(6)          NOT NULL COMMENT '创建时间',
    updated_time    datetime(6)          NOT NULL COMMENT '更新时间',
    CONSTRAINT uk_user_username UNIQUE (username)
) ENGINE = InnoDB
  DEFAULT CHARSET = Utf8mb4
  COLLATE = Utf8mb4_bin COMMENT '用户表';

insert into user(id, username, nickname, password, gender, last_login_ip, last_login_time, created_time, updated_time)
value ('1','superadmin','admin','123456','MAN','localhost','2023-03-07 20:37:36','2023-03-07 20:37:36',null);

insert into user(id, username, nickname, password, gender, last_login_ip, last_login_time, created_time, updated_time)
    value ('2','user_ljj','my_ljj','123456','MAN','localhost','2023-03-07 20:37:36','2023-03-07 20:37:36',null);