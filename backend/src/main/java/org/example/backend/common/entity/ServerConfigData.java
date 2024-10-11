/**
 * File Name: ServerConfigData.java
 * Description: 实体类，表示服务器配置数据的相关信息
 * Author: holic512
 * Created Date: 2024-10-10
 * Version: 1.0
 * Usage: 用于管理服务器的配置数据，包括配置项名称、JSON 格式的数据、配置类型、创建时间和更新时间。
 */
package org.example.backend.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "server_config_data")
public class ServerConfigData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 自增主键，唯一标识每一条记录

    @Column(name = "config_name", nullable = false, length = 255, columnDefinition = "VARCHAR(255) COMMENT '配置项名称'")
    private String configName;  // 配置项名称

    @Column(name = "config_json", nullable = false, columnDefinition = "JSON COMMENT '配置数据，采用JSON格式存储'")
    private String configJson;  // 配置数据，采用JSON格式存储

    @Column(name = "config_type", nullable = false, length = 50, columnDefinition = "VARCHAR(50) COMMENT '配置类型，例如 Redis、图片存储等'")
    private String configType;  // 配置类型，例如 Redis、图片存储等

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
