package org.example.backend.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 文件夹信息类（FolderInfo）
 * 描述：该类表示系统中的文件夹信息，包含文件夹名称、所属用户、父文件夹、简介、创建时间、更新时间等字段。
 * 通过 JPA 实体映射，该类与数据库中的 `folder_info` 表相对应。
 *
 * @author holic512
 * @version 1.1
 * @since 2024-10-14
 */
@Data
@Entity
@Table(name = "folder_info")
@EqualsAndHashCode(of = "id") // 只根据 id 生成 hashCode 和 equals 方法
@ToString(exclude = "userId") // 不包含 userId 字段的 toString 输出
public class FolderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 自增主键，唯一标识文件夹

    @Column(name = "user_id", nullable = false)
    private Long userId; // 用户 ID，关联用户表

    @Column(name = "folder_name", nullable = false, length = 100)
    private String folderName; // 文件夹名称

    @Column(name = "parent_id", nullable = false)
    private Long parentId = 0L; // 父文件夹 ID，若为 0 则表示根文件夹

    @Column(name = "description", length = 255)
    private String description; // 文件夹简介描述

    @Column(name = "folder_avatar", length = 4)
    private char[] folderAvatar; // 文件夹头像 emoji，存储单个 emoji 字符

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 创建时间

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 信息更新时间

    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted = 0; // 伪删除标志，0表示未删除，1表示已删除

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
