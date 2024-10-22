CREATE TABLE folder_info
(
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识文件夹',
    user_id     BIGINT       NOT NULL COMMENT '用户 ID，关联用户表',
    folder_name VARCHAR(100) NOT NULL COMMENT '文件夹名称',
    parent_id   BIGINT UNSIGNED  DEFAULT NULL COMMENT '父文件夹 ID，若为空则为顶级文件夹',
    description VARCHAR(255) COMMENT '文件夹简介描述',
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息更新时间',
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (parent_id) REFERENCES folder_info (id)
) COMMENT ='存储文件夹信息';
