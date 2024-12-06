CREATE TABLE favorite_note_info
(
    id                  BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '收藏记录唯一ID，自动递增',
    user_id             BIGINT          NOT NULL COMMENT '用户ID，关联users表',
    note_id             BIGINT UNSIGNED NOT NULL COMMENT '笔记ID，关联note_info表',
    favorite_folder_id  BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏文件夹ID，0表示根文件夹',

    favorite_status     TINYINT(1) NOT NULL DEFAULT 1 COMMENT '收藏状态，1表示已收藏，0表示已取消收藏',
    note_remark         TEXT COMMENT '用户对收藏笔记的备注，用户可以写些个人感想或标记',
    created_at          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间，默认当前时间',
    updated_at          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (note_id) REFERENCES note_info (id) ON DELETE CASCADE,

    UNIQUE KEY (user_id, note_id) COMMENT '保证每个用户只能收藏每个笔记一次'
) COMMENT = '用户收藏笔记信息表，存储用户收藏的笔记';

-- 为 user_id 字段创建索引
CREATE INDEX idx_user_id ON favorite_note_info (user_id) COMMENT '索引，用于优化根据 user_id 查询收藏记录';
