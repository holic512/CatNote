/**
 * File Name: PutNoteTreeService.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-20
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteTree.service;

import org.example.backend.user.note.noteTree.enums.PutFolderAvatarEnum;
import org.example.backend.user.note.noteTree.enums.PutFolderTitleEnum;
import org.example.backend.user.note.noteTree.enums.PutNoteAvatarEnum;
import org.example.backend.user.note.noteTree.enums.PutNoteTitleEnum;

public interface PutNoteTreeService {
    PutNoteAvatarEnum putNoteAvatar(Long userId, Long noteId, String avatar);

    PutNoteTitleEnum putNoteTitle(Long userId, Long noteId, String title);

    PutFolderAvatarEnum putFolderAvatar(Long userId, Long FolderId, String avatar);

    PutFolderTitleEnum putFolderTitle(Long userId, Long FolderId, String title);
}
