/**
 * CreateTime: 2024-08-28
 * Description: 用于用户登录请求体
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.Request.user;

public class AuthRequest {
    private String username;
    private String password;

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
