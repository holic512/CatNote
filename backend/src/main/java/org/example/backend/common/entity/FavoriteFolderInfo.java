/**
 * File Name: FavoriteFolderInfo.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-12-04
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "favorite_folder_info")
public class FavoriteFolderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; // 文件夹唯一ID，自动递增

    @Column(name = "user_id", nullable = false)
    private Long userId; // 用户ID，关联用户表

    @Column(name = "folder_name", nullable = false)
    private String folderName; // 文件夹名称，用户为文件夹指定名称

    @Column(name = "parent_id", nullable = false)
    private Long parentId = 0L; // 父文件夹ID，0表示根文件夹

    @Column(name = "favorite_folder_description")
    private String favoriteFolderDescription; // 收藏文件夹简介描述

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 创建时间，默认当前时间

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // 最后更新时间

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false; // 删除标志，0表示未删除，1表示已删除

    // 自动填充创建时间和更新时间
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
