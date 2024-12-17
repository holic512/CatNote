CREATE TABLE favorite_folder_info
(
    id              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '文件夹唯一ID，自动递增',
    user_id         BIGINT          NOT NULL COMMENT '用户ID，关联用户表',
    folder_name     VARCHAR(255) NOT NULL COMMENT '文件夹名称，用户为文件夹指定名称',
    parent_id       BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '父文件夹ID，0表示根文件夹',
    favorite_folder_description   VARCHAR(255) COMMENT '收藏文件夹简介描述',

    created_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',
    updated_at      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    is_deleted      TINYINT(1) NOT NULL DEFAULT 0 COMMENT '删除标志，0表示未删除，1表示已删除',

    FOREIGN KEY (user_id) REFERENCES users (id)
) COMMENT = '收藏文件夹表，存储用户的收藏文件夹信息';
