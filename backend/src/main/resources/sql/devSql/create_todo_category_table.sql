CREATE TABLE todo_category
(
    id         BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识每一条记录',
    user_id    BIGINT       NOT NULL COMMENT '用户 ID，关联用户表',
    name       varchar(255) NOT NULL COMMENT '分类名称',
    type       TINYINT UNSIGNED DEFAULT 0 COMMENT '分类类型，0-4分别默认,基础,成功,重要,严重',
    is_deleted TINYINT UNSIGNED DEFAULT 0 COMMENT '是否已删除，0:未删除，1:已删除', -- 添加 is_deleted 字段
    created_at DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认为当前时间戳',
    updated_at DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，默认值为当前时间戳，并在每次更新时自动修改',
    FOREIGN KEY (user_id) REFERENCES users (id)
) COMMENT ='存储用户待做的分类';