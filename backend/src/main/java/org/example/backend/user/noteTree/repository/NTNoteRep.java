/**
 * File Name: NTNoteRep.java
 * Description: 该接口用于定义与笔记信息相关的数据访问方法，主要通过 JPA 查询从数据库中获取笔记信息。
 * 特别是用于构建笔记树结构中的笔记节点。
 * Author: holic512
 * Created Date: 2024-10-21
 * Version: 1.0
 * Usage:
 * - 通过 `NTNoteRep` 接口可以查询用户的顶级笔记和指定文件夹下的笔记信息。
 * - 通常在服务层中使用这些方法来获取笔记信息，并将其转换为 `NoteTreeDto` 对象，以便在前端展示笔记树结构。
 */
package org.example.backend.user.noteTree.repository;

import org.example.backend.common.entity.NoteInfo;
import org.example.backend.user.noteTree.pojo.NoteTreeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NTNoteRep extends JpaRepository<NoteInfo, Long> {

    /**
     * 查询用户的所有顶级笔记信息。
     *
     * @param userId 用户 ID
     * @return 顶级笔记的列表，每个笔记信息封装在 NoteTreeDto 对象中
     */
    @Query("select new org.example.backend.user.noteTree.pojo.NoteTreeDto(n.id, n.noteTitle," +
            "org.example.backend.user.noteTree.enums.TreeType.NOTE, true) " +
            "FROM NoteInfo n " +
            "where n.userId = :userId and n.folderId IS NULL")
    List<NoteTreeDto> findTopLevelNotesByUserId(@Param("userId") Long userId);

    /**
     * 查询用户指定文件夹下的所有笔记信息。
     *
     * @param userId   用户 ID
     * @param folderId 文件夹 ID
     * @return 指定文件夹下的笔记列表，每个笔记信息封装在 NoteTreeDto 对象中
     */
    @Query("select new org.example.backend.user.noteTree.pojo.NoteTreeDto(n.id, n.noteTitle," +
            "org.example.backend.user.noteTree.enums.TreeType.NOTE, true) " +
            "FROM NoteInfo n " +
            "where n.userId = :userId and n.folderId = :folderId")
    List<NoteTreeDto> findNotesByUserIdAndFolderId(@Param("userId") Long userId, @Param("folderId") Long folderId);


    @Query("SELECT n.id FROM NoteInfo n WHERE n.id = :id")
    Long findFolderIdById(@Param("id") Long id);


}