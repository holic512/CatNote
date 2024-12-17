CREATE TABLE note_info
(
    id             BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '笔记唯一ID，自动递增',
    user_id        BIGINT          NOT NULL COMMENT '用户 ID，关联用户表',
    folder_id      BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '父文件夹ID，为0代表根文件夹ID，默认为0',

    note_title     VARCHAR(16) COMMENT '笔记名称，最长16个字符',
    note_summary   TEXT COMMENT '笔记简介，可以存储长文本',
    note_avatar    CHAR(4) COMMENT '笔记头像 emoji 字符',

    note_cover_url CHAR(5) COMMENT '笔记背景的id',

    note_password  VARCHAR(255)             DEFAULT NULL COMMENT '笔记密码，可为空',

    note_type      TINYINT(1)      NOT NULL COMMENT '笔记类型，使用TINYINT(1)来区分类型，1表示正常笔记',

    created_at     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',
    updated_at     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    is_deleted     TINYINT(1)      NOT NULL DEFAULT 0 COMMENT '伪删除标志，0表示未删除，1表示已删除',

    FOREIGN KEY (user_id) REFERENCES users (id)
) COMMENT ='存储用户笔记信息的表';

-- 为 user_id 创建索引，并添加注释
CREATE INDEX idx_user_id ON note_info (user_id) COMMENT '索引，用于优化 folder_id 的查询';
