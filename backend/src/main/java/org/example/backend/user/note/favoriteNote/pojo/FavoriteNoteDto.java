/**
 * File Name: FavoriteNoteDto.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-12-05
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.favoriteNote.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteNoteDto {

    private Long id; // 收藏记录唯一ID

    private Long userId; // 用户ID

    private Long noteId; // 笔记ID

    private Long favoriteFolderId; // 收藏文件夹ID

    private boolean favoriteStatus; // 收藏状态

    private String noteRemark; // 用户对收藏笔记的备注

    private LocalDateTime createdAt; // 收藏时间

    private LocalDateTime updatedAt; // 最后更新时间


    // 构造函数，用于根据 FavoriteNoteInfo 初始化 DTO
    public FavoriteNoteDto(Long id, Long userId, Long noteId, Long favoriteFolderId,
                           boolean favoriteStatus, String noteRemark, LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.noteId = noteId;
        this.favoriteFolderId = favoriteFolderId;
        this.favoriteStatus = favoriteStatus;
        this.noteRemark = noteRemark;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
