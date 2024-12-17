CREATE TABLE server_config
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识每一条记录',
    image_storage_mode TINYINT(1) NOT NULL COMMENT '图片存储模式，0: 本地存储，1: 阿里OSS',
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认为当前时间戳',
    updated_at         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，默认值为当前时间戳，并在每次更新时自动修改'
) COMMENT ='存储服务器的配置表';
