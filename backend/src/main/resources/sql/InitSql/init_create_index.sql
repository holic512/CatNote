-- 创建 note_info  的索引
CREATE INDEX idx_user_id ON note_info (user_id) COMMENT '索引，用于优化根据 user_id 的查询';

-- 创建 favorite_note_info  的索引
CREATE INDEX idx_user_id ON favorite_note_info (user_id) COMMENT '索引，用于优化根据 user_id 查询收藏记录';

