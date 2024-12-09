/**
 * @file PutNoteTreeService.java
 * @brief 服务接口用于更新笔记树中的笔记和文件夹的相关信息。
 * @details 提供了一系列方法来更新笔记树中各个节点（如笔记和文件夹）的属性，如头像、标题和简介。
 * 每个方法都接受用户ID、目标ID（笔记或文件夹ID）以及需要更新的具体内容。
 * @author holic512
 * @date 2024-11-20
 * @version 1.0
 */

package org.example.backend.user.note.noteTree.service;

import org.example.backend.user.note.noteTree.enums.*;

/**
 * 接口定义了用于更新笔记树结构中笔记和文件夹信息的服务方法。
 */
public interface PutNoteTreeService {

    /**
     * 更新指定笔记的头像。
     *
     * @param userId 用户的唯一标识符。
     * @param noteId 笔记的唯一标识符。
     * @param avatar 新的头像路径或URL。
     * @return 返回更新操作的状态码枚举，指示操作结果。
     */
    PutNoteAvatarEnum putNoteAvatar(Long userId, Long noteId, String avatar);

    /**
     * 更新指定笔记的名称。
     *
     * @param userId 用户的唯一标识符。
     * @param noteId 笔记的唯一标识符。
     * @param title  新的笔记标题。
     * @return 返回更新操作的状态码枚举，指示操作结果。
     */
    PutNoteTitleEnum putNoteTitle(Long userId, Long noteId, String title);

    /**
     * 更新指定文件夹的头像。
     *
     * @param userId   用户的唯一标识符。
     * @param folderId 文件夹的唯一标识符。
     * @param avatar   新的头像路径或URL。
     * @return 返回更新操作的状态码枚举，指示操作结果。
     */
    PutFolderAvatarEnum putFolderAvatar(Long userId, Long folderId, String avatar);

    /**
     * 更新指定文件夹的标题。
     *
     * @param userId   用户的唯一标识符。
     * @param folderId 文件夹的唯一标识符。
     * @param title    新的文件夹标题。
     * @return 返回更新操作的状态码枚举，指示操作结果。
     */
    PutFolderTitleEnum putFolderTitle(Long userId, Long folderId, String title);

    /**
     * 更新指定笔记的简介。
     *
     * @param userId      用户的唯一标识符。
     * @param noteId      笔记的唯一标识符。
     * @param description 新的笔记简介内容。
     * @return 返回更新操作的状态码枚举，指示操作结果。
     */
    PutUNTContextEnum putNoteDescription(Long userId, Long noteId, String description);

    /**
     * 更新指定文件夹的简介。
     *
     * @param userId      用户的唯一标识符。
     * @param folderId    文件夹的唯一标识符。
     * @param description 新的文件夹简介内容。
     * @return 返回更新操作的状态码枚举，指示操作结果。
     */
    PutUNTContextEnum putFolderDescription(Long userId, Long folderId, String description);
}