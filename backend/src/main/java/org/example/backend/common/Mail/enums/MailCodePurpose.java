/**
 * CreateTime: 2024-08-29
 * Description: 验证码用途枚举
 * Version: 1.0
 * Author: holic512
 * Usage:
 * 通过枚举区分 验证码使用行为 来区分不同的 邮件模板
 */
package org.example.backend.common.Mail.enums;

public enum MailCodePurpose {
    // 用户登录
    UserLogin,

    // 用户注册
    UserRegister,

    // 用户修改密码
    UserSetPassword,

    // 管理员登录
    AdminLogin,
}
