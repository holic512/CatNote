/**
 * File Name: RegStatus.java
 * Description: 管理员授权状态
 * Author: holic512
 * Created Date: 2024-08-29
 * Version: 1.0
 * Usage:
 * 这个枚举用于区分于管理员注册服务状态的行为,成功状态:AuthServiceEnum.success
 */
package org.example.backend.enums.admin;

public enum AuthServiceEnum {
    Success,                // 成功
    EmailAlreadyExists,     // 邮箱已存在
    UserAlreadyExists,      // 用户已存在
    EmailSendFailure,       // 邮箱发送失败
    EmailNotFound,          // 邮箱不存在
    JsonParseError,         // Json解析错误
    RegIdNotFound,          // 注册标识符未找到
    INVALID_CODE,           // 验证码无效
    UserNotExists,          // 用户不存在
    INCORRECT,              // 密码错误(与数据库存储比对不符)

}
