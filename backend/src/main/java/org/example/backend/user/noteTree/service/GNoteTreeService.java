/**
 * File Name: GNoteTreeService.java
 * Description: 该接口定义了与笔记树相关的服务方法，主要用于查询用户的文件夹和笔记信息，并构建笔记树结构。
 * Author: holic512
 * Created Date: 2024-10-16
 * Version: 1.0
 * Usage:
 *      - 通过 `GNoteTreeService` 接口可以查询用户的文件夹和笔记信息。
 *      - 通常在控制器层中调用这些方法，获取文件夹和笔记的层级结构，并返回给前端进行展示。
 *      - 前端可以根据返回的 `NoteTreeDto` 列表构建和展示笔记树结构。
 */
package org.example.backend.user.noteTree.service;

import org.example.backend.user.noteTree.pojo.NoteFolderDto;
import org.example.backend.user.noteTree.pojo.NoteTreeDto;

import java.util.List;

public interface GNoteTreeService {

    /**
     * 查询用户指定父文件夹下的文件夹和笔记信息。
     *
     * @param userId 用户 ID
     * @param parentId 父文件夹 ID，如果为空则查询顶级文件夹
     * @return 文件夹和笔记的列表，每个节点信息封装在 NoteTreeDto 对象中
     */
    List<NoteTreeDto> getNoteTreeList(long userId, Long parentId);

    /**
     * 查询用户指定文件夹下的笔记信息
     *
     * @param id       用户id
     * @param folderId 文件夹id
     * @return
     */
    List<NoteFolderDto> getFolderList(long id, Long folderId);
}