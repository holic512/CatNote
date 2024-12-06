CREATE TABLE folder_info
(
    id            BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识文件夹',
    user_id       BIGINT          NOT NULL COMMENT '用户 ID，关联用户表',
    parent_id     BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '父文件夹ID,为0代表根文件夹ID,默认为0',
    folder_name   VARCHAR(100)    NOT NULL COMMENT '文件夹名称',
    description   VARCHAR(255) COMMENT '文件夹简介描述',
    folder_avatar CHAR(4) COMMENT '文件夹头像 emoji 字符',
    created_at    DATETIME                 DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    DATETIME                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息更新时间',
    is_deleted    TINYINT UNSIGNED         DEFAULT 0 COMMENT '是否已删除，0:未删除，1:已删除', -- 添加 is_deleted 字段
    FOREIGN KEY (user_id) REFERENCES users (id)
) COMMENT ='存储文件夹信息';
