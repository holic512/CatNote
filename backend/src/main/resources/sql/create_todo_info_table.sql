CREATE TABLE todo_info
(
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识每一条记录',
    user_id     BIGINT       NOT NULL COMMENT '用户 ID，关联用户表',
    category_id BIGINT       NOT NULL COMMENT '分类 ID，伪关联 todo_category 表,为0则是未分类',
    title       VARCHAR(255) NOT NULL COMMENT '待办事项标题',
    description TEXT COMMENT '待办事项描述',
    start_date  DATETIME COMMENT '开始日期',
    due_date    DATETIME COMMENT '截止日期',

    status      tinyint unsigned default 0 comment '当前待做状态,0:未完成,1:已完成',
    is_deleted  TINYINT UNSIGNED DEFAULT 0 COMMENT '是否已删除，0:未删除，1:已删除', -- 添加 is_deleted 字段

    created_at  DATETIME         DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认为当前时间戳',
    updated_at  DATETIME         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，默认值为当前时间戳，并在每次更新时自动修改',

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
) COMMENT = '存储用户待做事项';
