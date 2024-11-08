/**
 * File Name: NoteFolderDto.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-10-28
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteTree.pojo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteFolderDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String folderName;
    private Long parentId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NoteFolderDto(Long id, Long userId, String folderName, Long parentId, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.folderName = folderName;
        this.parentId = parentId;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
