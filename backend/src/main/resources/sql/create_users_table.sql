CREATE TABLE users
(
    id          BIGINT                                NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键，自增ID',
    uid         VARCHAR(50)                           NOT NULL UNIQUE COMMENT '唯一用户ID，用户的唯一标识',
    username    VARCHAR(50)                           NOT NULL UNIQUE COMMENT '用户名，唯一',
    password    VARCHAR(255)                          NOT NULL COMMENT '用户密码，经过加密',
    email       VARCHAR(100)                          NOT NULL UNIQUE COMMENT '用户邮箱，唯一',
    status      ENUM ('ACTIVE', 'DISABLED', 'BANNED') NOT NULL DEFAULT 'ACTIVE' COMMENT '用户状态，枚举类型：ACTIVE, DISABLED, BANNED',
    has_profile TINYINT(1)                            NOT NULL DEFAULT 0 COMMENT '是否填写个人信息，0 表示未填写，1 表示已填写',
    created_at  TIMESTAMP                             NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，记录用户账号的创建时间',
    updated_at  TIMESTAMP                             NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，记录最近一次修改的时间'
) COMMENT ='用户表，存储用户的基本信息，包含状态和个人信息填写状态';
