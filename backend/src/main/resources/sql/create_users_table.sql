CREATE TABLE users
(
    id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键，自增ID',
    uid        VARCHAR(50)  NOT NULL UNIQUE COMMENT '唯一用户ID，用户的唯一标识',
    username   VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名，唯一',
    password   VARCHAR(255) NOT NULL COMMENT '用户密码，经过加密',
    email      VARCHAR(100) NOT NULL UNIQUE COMMENT '用户邮箱，唯一',
    status     VARCHAR(100) NOT NULL COMMENT '用户状态，表示用户当前状态（如正常、停用、封禁等）',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，记录用户账号的创建时间',
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，记录最近一次修改的时间'
) COMMENT='用户表，存储用户的基本信息';
