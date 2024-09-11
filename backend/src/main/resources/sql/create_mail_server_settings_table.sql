CREATE TABLE mail_server_settings
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
