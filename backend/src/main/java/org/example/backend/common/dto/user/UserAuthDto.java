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
import org.example.backend.common.enums.user.UserStatusEnum;

@Data
public class UserAuthDto {
    private long id;
    private String uid;
    private String password;
    private Integer status;

    public UserAuthDto(long id, String uid, String password, Integer status) {
        this.id = id;
        this.uid = uid;
        this.password = password;
        this.status = status;
    }

    public UserAuthDto(String uid, Integer status) {
        this.uid = uid;
        this.status = status;
    }
}
