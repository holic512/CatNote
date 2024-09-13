package org.example.backend.common.entity;

import jakarta.persistence.*;
import org.example.backend.user.enums.StatusEnum;

import java.time.LocalDateTime;

@Entity
@Table(name = "users") // 指定该类映射到数据库表
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String uid;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)  // 状态使用枚举类型存储
    @Column(nullable = false, length = 100)
    private StatusEnum status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 可以在实体类中添加 @PrePersist 和 @PreUpdate 方法来自动处理创建时间和更新时间
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public User() {
    }

    public User(String uid, String password, StatusEnum status) {
        this.uid = uid;
        this.password = password;
        this.status = status;
    }

    public User(String uid, StatusEnum status) {
        this.uid = uid;
        this.status = status;
    }

    // 被用于 查询管理员后台-用户管理-用户数据
    public User(Long id, String uid, String username, String email, StatusEnum status) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.status = status;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
