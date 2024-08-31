/**
 * CreateTime: 2024-08-27
 * Description: 用户登录的数据传输对象
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.Dto.user;

public class AuthDto {
    private String uid;
    private String password;

    public AuthDto(String uid) {
        this.uid = uid;
    }

    public AuthDto(String uid, String password) {
        this.uid = uid;
        this.password = password;
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
