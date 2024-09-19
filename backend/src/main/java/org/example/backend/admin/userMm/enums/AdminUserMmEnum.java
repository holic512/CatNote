/**
 * File Name: AdminUserMmEnum.java
 * Description: 用于 用户管理 服务类 返回状态枚举
 * Author: holic512
 * Created Date: 2024-09-19
 * Version: 1.0
 * Usage:
 * 用户管理 服务类 返回状态枚举,控制层根据返回状态,向前端返回不同信息
 */
package org.example.backend.admin.userMm.enums;

public enum AdminUserMmEnum {
    Success,                // 成功
    EmailAlreadyExists,     // 邮箱已存在
    UserAlreadyExists,      // 用户已存在

}
