CREATE TABLE note_info
(
    id            BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '笔记唯一ID，自动递增',                       -- 添加注释
    user_id       BIGINT       NOT NULL COMMENT '用户 ID，关联用户表',
    folder_id     BIGINT UNSIGNED COMMENT '父文件夹ID,外键为 folder_info id',                                     -- 添加注释
    note_title    VARCHAR(255) NOT NULL COMMENT '笔记名称，最长255个字符',                                         -- 添加注释
    note_summary  TEXT COMMENT '笔记简介，可以存储长文本',                                                         -- 添加注释
    note_password VARCHAR(255)          DEFAULT NULL COMMENT '笔记密码，可为空',                                   -- 添加注释
    note_type     TINYINT(1)   NOT NULL COMMENT '笔记类型，使用TINYINT(1)来区分类型,当为1时,为正常笔记',           -- 添加注释
    created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',                -- 使用 DATETIME
    updated_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间', -- 使用 DATETIME

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (folder_id) REFERENCES folder_info (id)
) COMMENT ='存储用户笔记信息的表';

-- 为 folder_id 创建索引，并添加注释
CREATE INDEX idx_folder_id ON note_info (folder_id) COMMENT '索引，用于优化 folder_id 的查询';
