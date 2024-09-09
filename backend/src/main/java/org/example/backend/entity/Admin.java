/**
 * File Name: Admin.java
 * Description: 管理员信息类
 * Author: holic512
 * Created Date: 2024-09-04
 * Version: 1.0
 * Usage:
 * 用于存储管理员的 授权信息
 */
package org.example.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins") // 指定该类映射到数据库表 "auth"
public class Admin {

    @Id // 主键 id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 指定主键生成策略为自增
    // 列名为 "id"，不可为 null，不可更新
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // 列名为 "uid"，唯一，不可为 null，最大长度为 50
    @Column(name = "uid", unique = true, nullable = false, length = 50)
    private String uid;

    // 列名为 "username"，唯一，不可为 null，最大长度为 50
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    // 列名为 "password"，不可为 null，最大长度为 255
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // 列名为 "email"，唯一，不可为 null，最大长度为 100
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    // 列名为 "created_at"，不可为 null，不可更新
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 列名为 "updated_at"，不可为 null
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

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
