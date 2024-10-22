/**
 * File Name: FolderInfo.java
 * Description: 该类用于表示系统中的文件夹信息，包括文件夹的基本属性如名称、所属用户、父文件夹等。
 *              通过 JPA 实体映射，该类与数据库中的 `folder_info` 表相对应。
 * Author: holic512
 * Created Date: 2024-10-14
 * Version: 1.0
 * Usage:
 *      - 通过 `FolderInfo` 对象可以创建、查询、更新和删除文件夹信息。
 *      - 通常在用户管理文件夹结构时使用，例如在用户界面中展示文件夹树、允许用户创建新文件夹等。
 */
package org.example.backend.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "folder_info")
public class FolderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 自增主键，唯一标识文件夹

    @Column(name = "user_id", nullable = false)
    private Long userId; // 用户 ID，关联用户表

    @Column(name = "folder_name", nullable = false, length = 100)
    private String folderName; // 文件夹名称

    @Column(name = "parent_id")
    private Long parentId; // 父文件夹 ID，若为空则为顶级文件夹

    @Column(name = "description")
    private String description; // 文件夹简介描述

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 创建时间

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 信息更新时间
}
