/**
 * CreateTime: 2024-08-27
 * Description: 用户登录的数据传输对象
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.user.dto;

import org.example.backend.common.enums.UserStatusEnum;

public class AuthDto {
    private String uid;
    private String password;
    UserStatusEnum status;
    String code;


    public AuthDto(String uid, UserStatusEnum status, String code) {
        this.uid = uid;
        this.status = status;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AuthDto(String uid, String password) {
        this.uid = uid;
        this.password = password;
    }

    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthDto [uid=" + uid + ", password=" + password + "]";
    }
}
