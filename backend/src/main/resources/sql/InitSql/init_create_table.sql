-- 创建 mail_server_settings 表
CREATE TABLE IF NOT EXISTS mail_server_settings
(
    id                BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键 ID',
    host              VARCHAR(255) NOT NULL COMMENT '邮件服务器主机名',
    port              INT          NOT NULL COMMENT '邮件服务器端口',
    username          VARCHAR(255) NOT NULL COMMENT '邮箱用户名',
    password          VARCHAR(255) NOT NULL COMMENT '邮箱密码',
    auth              BOOLEAN      NOT NULL DEFAULT TRUE COMMENT '是否需要认证',
    security_protocol VARCHAR(50)  NOT NULL COMMENT '加密协议类型',
    created_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账号创建时间',
    updated_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息更新时间'
);


-- 创建 server_config 表
CREATE TABLE IF NOT EXISTS server_config
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识每一条记录',
    image_storage_mode TINYINT(1) NOT NULL COMMENT '图片存储模式，0: 本地存储，1: 阿里OSS,对应配置dataType:ImageStorageMode',
    mail_mode          TINYINT(1) NOT NULL COMMENT '邮箱模式，0: 开启，1: 关闭,对应配置dataType:MailMode',
    created_at         DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认为当前时间戳',
    updated_at         DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，默认值为当前时间戳，并在每次更新时自动修改'
) COMMENT ='存储服务器的配置表';

-- 创建 server_config_data 表
CREATE TABLE IF NOT EXISTS server_config_data
(
    id          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识每一条记录',
    config_name VARCHAR(255) NOT NULL COMMENT '配置项名称',
    config_json JSON         NOT NULL COMMENT '配置数据，采用JSON格式存储',
    config_type VARCHAR(50)  NOT NULL COMMENT '配置类型，例如 Redis、图片存储等',
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，默认为当前时间戳',
    updated_at  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，默认值为当前时间戳，并在每次更新时自动修改'
) COMMENT ='存储配置的详细信息，包含配置项名称、数据和类型等信息';


-- 创建 users 表
CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键，自增ID',
    uid         VARCHAR(50)  NOT NULL UNIQUE COMMENT '唯一用户ID，用户的唯一标识',
    username    VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名，唯一',
    password    VARCHAR(255) NOT NULL COMMENT '用户密码，经过加密',
    email       VARCHAR(100) NOT NULL UNIQUE COMMENT '用户邮箱，唯一',
    status      TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '用户状态，ACTIVE-0, DISABLED-1, BANNED-2',
    has_profile TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '是否填写个人信息，0 表示未填写，1 表示已填写',
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，记录用户账号的创建时间',
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间，记录最近一次修改的时间'
) COMMENT ='用户表，存储用户的基本信息，包含状态和个人信息填写状态';

-- 创建 admin 表
CREATE TABLE IF NOT EXISTS admins
(
    id         BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键 ID',
    uid        VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户 ID',
    username   VARCHAR(50)  NOT NULL UNIQUE COMMENT '用户名',
    password   VARCHAR(255) NOT NULL COMMENT '密码',
    email      VARCHAR(100) NOT NULL UNIQUE COMMENT '邮箱',
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账户创建时间',
    updated_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息更新时间'
);

-- 创建 note_info 表
CREATE TABLE IF NOT EXISTS note_info
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



-- 创建 folder_info 表
CREATE TABLE IF NOT EXISTS folder_info
(
    id            BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键，唯一标识文件夹',
    user_id       BIGINT          NOT NULL COMMENT '用户 ID，关联用户表',
    parent_id     BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '父文件夹ID,为0代表根文件夹ID,默认为0',
    folder_name   VARCHAR(100)    NOT NULL COMMENT '文件夹名称',
    description   VARCHAR(255) COMMENT '文件夹简介描述',
    folder_avatar CHAR(4) COMMENT '文件夹头像 emoji 字符',
    created_at    DATETIME                 DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at    DATETIME                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息更新时间',
    is_deleted    TINYINT UNSIGNED         DEFAULT 0 COMMENT '是否已删除，0:未删除，1:已删除',
    FOREIGN KEY (user_id) REFERENCES users (id)
) COMMENT ='存储文件夹信息';


-- 创建 favorite_folder_info 表
CREATE TABLE IF NOT EXISTS favorite_folder_info
(
    id                          BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '文件夹唯一ID，自动递增',
    user_id                     BIGINT          NOT NULL COMMENT '用户ID，关联用户表',
    folder_name                 VARCHAR(255)    NOT NULL COMMENT '文件夹名称，用户为文件夹指定名称',
    parent_id                   BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '父文件夹ID，0表示根文件夹',
    favorite_folder_description VARCHAR(255) COMMENT '收藏文件夹简介描述',
    created_at                  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，默认当前时间',
    updated_at                  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    is_deleted                  TINYINT(1)      NOT NULL DEFAULT 0 COMMENT '删除标志，0表示未删除，1表示已删除',
    FOREIGN KEY (user_id) REFERENCES users (id)
) COMMENT = '收藏文件夹表，存储用户的收藏文件夹信息';

-- 创建 favorite_note_info 表
CREATE TABLE IF NOT EXISTS favorite_note_info
(
    id                 BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '收藏记录唯一ID，自动递增',
    user_id            BIGINT          NOT NULL COMMENT '用户ID，关联users表',
    note_id            BIGINT UNSIGNED NOT NULL COMMENT '笔记ID，关联note_info表',
    favorite_folder_id BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏文件夹ID，0表示根文件夹',
    favorite_status    TINYINT(1)      NOT NULL DEFAULT 1 COMMENT '收藏状态，1表示已收藏，0表示已取消收藏',
    note_remark        TEXT COMMENT '用户对收藏笔记的备注，用户可以写些个人感想或标记',
    created_at         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间，默认当前时间',
    updated_at         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (note_id) REFERENCES note_info (id) ON DELETE CASCADE,
    UNIQUE KEY (user_id, note_id) COMMENT '保证每个用户只能收藏每个笔记一次'
) COMMENT = '用户收藏笔记信息表，存储用户收藏的笔记';




-- 创建 todo_category 表
CREATE TABLE IF NOT EXISTS todo_category
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

-- 创建 todo_info 表
CREATE TABLE IF NOT EXISTS todo_info
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
