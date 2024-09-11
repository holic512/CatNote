/**
 * File Name: AuthDto.java
 * Description: 用于 管理员登录的 授权信息数据体
 * Author: holic512
 * Created Date: 2024-09-11
 * Version: 1.0
 * Usage:
 * 用于 管理员登录的 授权信息数据体
 */
package org.example.backend.admin.dto;

public class AuthDto {
    private String uid;
    private String email;
    private String password;

    public AuthDto(String uid, String email, String password) {
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
