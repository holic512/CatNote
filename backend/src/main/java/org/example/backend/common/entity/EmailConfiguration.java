/**
 * CreateTime: 2024-08-29
 * Description: 邮箱配置实体类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.example.backend.common.enums.mail.MailSecurityProtocol;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "mail_server_settings")
public class EmailConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "host", nullable = false, length = 255)
    private String host;  // 邮件服务器主机名

    @Column(name = "port", nullable = false)
    private int port;  // 邮件服务器端口

    @Column(name = "username", nullable = false, length = 255)
    private String username;  // 邮箱用户名

    @Column(name = "password", nullable = false, length = 255)
    private String password;  // 邮箱密码

    @Column(name = "auth", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean auth = true;  // 是否需要认证

    @Enumerated(EnumType.STRING)
    @Column(name = "security_protocol", nullable = false, length = 50)
    private MailSecurityProtocol securityProtocol; // 加密协议类型

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;  // 账号创建时间

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;  // 信息更新时间


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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


    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public MailSecurityProtocol getMailSecurityProtocol() {
        return securityProtocol;
    }

    public void setMailSecurityProtocol(MailSecurityProtocol mailSecurityProtocol) {
        securityProtocol = mailSecurityProtocol;
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

