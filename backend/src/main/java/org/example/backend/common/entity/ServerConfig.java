/**
 * File Name: ServerConfig.java
 * Description: 实体类，表示服务器配置的相关信息
 * Author: holic512
 * Created Date: 2024-10-10
 * Version: 1.0
 * Usage: 用于管理服务器的配置，包括图片存储模式、创建时间和更新时间。
 */
package org.example.backend.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "server_config")
public class ServerConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 自增主键，唯一标识每一条记录

    @Column(name = "image_storage_mode", nullable = false, columnDefinition = "TINYINT(1) COMMENT '图片存储模式，0: 本地存储，1: 阿里OSS'")
    private Integer imageStorageMode; // 图片存储模式，0: 本地存储，1: 阿里OSS

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;  // 记录创建时间，默认为当前时间戳

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // 记录更新时间，默认值为当前时间戳，并在每次更新时自动修改

    // 在持久化之前自动设置创建时间
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now(); // 也设置更新时间
    }

    // 在更新之前自动设置更新时间
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
