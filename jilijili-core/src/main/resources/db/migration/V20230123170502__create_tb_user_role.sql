drop table if exists user_role;
CREATE TABLE user_role
(
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    role_id VARCHAR(32) NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB
  DEFAULT CHARSET = Utf8mb4
  COLLATE = Utf8mb4_bin COMMENT '用户角色表';

insert into user_role(user_id, role_id)
value (1,1);
insert into user_role(user_id, role_id)
    value (2,2)