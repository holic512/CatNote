/**
 * File Name: FavoriteFolderDto.java
 * Description: DTO for transferring favorite folder data
 * Author: lv
 * Created Date: 2024-12-06
 * Version: 1.0
 * Usage: Used to transfer favorite folder data between layers
 */
package org.example.backend.user.note.favoriteNote.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteFolderDto {

    private Long id; // 文件夹唯一ID

    private Long userId; // 用户ID

    private String folderName; // 文件夹名称

    private Long parentId; // 父文件夹ID

    private String favoriteFolderDescription; // 收藏文件夹简介描述

    private LocalDateTime createdAt; // 创建时间

    private LocalDateTime updatedAt; // 更新时间

    private boolean isDeleted; // 删除标志

    // 构造函数，用于根据 FavoriteFolderInfo 初始化 DTO
    public FavoriteFolderDto(Long id, Long userId,
                             String folderName, Long parentId,
                             String favoriteFolderDescription, LocalDateTime createdAt,
                             LocalDateTime updatedAt, boolean isDeleted) {
        this.id = id;
        this.userId = userId;
        this.folderName = folderName;
        this.parentId = parentId;
        this.favoriteFolderDescription = favoriteFolderDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }
}

