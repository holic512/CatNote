/**
 * File Name: Admin.java
 * Description: 管理员信息类
 * Author: holic512
 * Created Date: 2024-09-04
 * Version: 1.0
 * Usage:
 * 用于存储管理员的 授权信息
 */
package org.example.backend.common.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")  // 指定该类映射到数据库表 "admins"
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;  // 主键 ID

    @Column(name = "uid", unique = true, nullable = false, length = 50)
    private String uid;  // 用户 ID

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;  // 用户名

    @Column(name = "password", nullable = false, length = 255)
    private String password;  // 密码

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;  // 邮箱

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 账户创建时间

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;  // 信息更新时间

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Admin(String uid, String email, String password) {
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
