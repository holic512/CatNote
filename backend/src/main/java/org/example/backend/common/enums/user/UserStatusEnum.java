/**
 * File Name: StatusEnum.java
 * Description: 用于 用户账号状态枚举
 * Author: holic512
 * Created Date: 2024-09-04
 * Version: 1.0
 * Usage:
 * 区分 用户 正常 停用 封禁 等行为
 */
package org.example.backend.common.enums.user;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
    ACTIVE("ACTIVE"),    // 对应数据库中的 ACTIVE
    DISABLED("DISABLED"), // 对应数据库中的 DISABLED
    BANNED("BANNED");    // 对应数据库中的 BANNED

    private final String value;

    UserStatusEnum(String value) {
        this.value = value;
    }

}

