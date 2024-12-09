/**
 * File Name: NoteInfo.java
 * Description: 该类用于表示系统中的笔记信息，包括笔记的基本属性如标题、摘要、所属用户、所属文件夹等。
 * 通过 JPA 实体映射，该类与数据库中的 `note_info` 表相对应。
 * Author: holic512
 * Created Date: 2024-10-21
 * Version: 1.1
 * Usage:
 * - 通过 `NoteInfo` 对象可以创建、查询、更新和删除笔记信息。
 * - 通常在用户管理笔记时使用，例如在用户界面中展示笔记列表、允许用户创建新笔记、编辑现有笔记等。
 * - 笔记可以属于某个文件夹，也可以设置密码保护，以增加安全性。
 * - `is_deleted` 字段用于支持伪删除功能，值为 1 表示已删除，0 表示未删除。
 */
package org.example.backend.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "note_info")
public class NoteInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 笔记唯一ID

    @Column(name = "user_id", nullable = false)
    private Long userId; // 用户 ID，关联用户表

    @Column(name = "folder_id")
    private Long folderId; // 父文件夹ID, 外键为 folder_info id

    @Column(name = "note_title", nullable = false)
    private String noteTitle; // 笔记名称

    @Column(name = "note_summary")
    // 笔记简介
    private String noteSummary;

    @Column(name = "note_avatar", length = 4)
    private char[] noteAvatar; // 文件夹头像 emoji，存储单个 emoji 字符

    @Column(name = "note_password")
    private String notePassword; // 笔记密码

    @Column(name = "note_type", nullable = false)
    private Integer noteType; // 笔记类型

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 创建时间

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 更新时间

    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted = 0; // 伪删除标志，0表示未删除，1表示已删除

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
}
