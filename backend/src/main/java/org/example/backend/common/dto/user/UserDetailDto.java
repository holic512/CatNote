/**
 * File Name: UserDetailDto.java
 * Description: jpa 获取 用户详细 信息
 * Author: holic512
 * Created Date: 2024-09-19
 * Version: 1.0
 * Usage:
 * jpa 获取 用户详细 信息
 */
package org.example.backend.common.dto.user;

import lombok.Data;
import org.example.backend.common.enums.user.UserStatusEnum;

@Data
public class UserDetailDto {
    private Long id;
    private String uid;
    private String username;
    private String email;
    private UserStatusEnum status;

    public UserDetailDto(Long id, String uid, String username, String email, UserStatusEnum status) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.status = status;
    }

}
