-- 默认 本地存储 关闭邮箱
INSERT INTO server_config (image_storage_mode, mail_mode)
VALUES (0, 1);

-- 插入 管理员用户 - 用户名 admin 密码 admin(加密后)
INSERT INTO admins (uid, username, password, email)
VALUES ('admin123', 'admin', 'qrTFTn025nuqO+is25SAGw==:XIB1QiYm2TlTq/i/iTsFEULpsRKLuTtiWmKQG4a/84A=',
        'admin@example.com');
