CREATE TABLE server_config_data
(
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识每一条记录',
    config_name VARCHAR(255) NOT NULL COMMENT '配置项名称',
    config_json JSON         NOT NULL COMMENT '配置数据，采用JSON格式存储',
    config_type VARCHAR(50)  NOT NULL COMMENT '配置类型，例如 Redis、图片存储等',
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认为当前时间戳',
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，默认值为当前时间戳，并在每次更新时自动修改'
) COMMENT ='存储配置的详细信息，包含配置项名称、数据和类型等信息';
