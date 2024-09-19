/**
 * File Name: UserAuthDto.java
 * Description: jpa 获取 用户授权 信息
 * Author: holic512
 * Created Date: 2024-09-19
 * Version: 1.0
 * Usage:
 * jpa 获取 用户授权 信息
 */
package org.example.backend.common.dto.user;

import lombok.Data;
import org.example.backend.common.enums.UserStatusEnum;

@Data
public class UserAuthDto {
    private String uid;
    private String password;
    private UserStatusEnum status;

    public UserAuthDto(String uid, String password, UserStatusEnum status) {
        this.uid = uid;
        this.password = password;
        this.status = status;
    }

    public UserAuthDto(String uid, UserStatusEnum status) {
        this.uid = uid;
        this.status = status;
    }
}
