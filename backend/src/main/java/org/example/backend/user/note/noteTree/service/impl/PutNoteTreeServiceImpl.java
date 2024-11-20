/**
 * File Name: PutNoteTreeServiceImpl.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-20
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteTree.service.impl;

import org.example.backend.common.entity.FolderInfo;
import org.example.backend.common.entity.NoteInfo;
import org.example.backend.user.note.noteTree.enums.PutFolderAvatarEnum;
import org.example.backend.user.note.noteTree.enums.PutFolderTitleEnum;
import org.example.backend.user.note.noteTree.enums.PutNoteAvatarEnum;
import org.example.backend.user.note.noteTree.enums.PutNoteTitleEnum;
import org.example.backend.user.note.noteTree.repository.NTFolderRep;
import org.example.backend.user.note.noteTree.repository.NTNoteRep;
import org.example.backend.user.note.noteTree.service.PutNoteTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PutNoteTreeServiceImpl implements PutNoteTreeService {

    private final NTNoteRep ntNoteRep;
    private final NTFolderRep ntFolderRep;

    @Autowired
    public PutNoteTreeServiceImpl(NTNoteRep ntNoteRep, NTFolderRep ntFolderRep) {
        this.ntNoteRep = ntNoteRep;
        this.ntFolderRep = ntFolderRep;
    }

    @Override
    public PutNoteAvatarEnum putNoteAvatar(Long userId, Long noteId, String avatar) {
        // 检测笔记是否存在并获取其所属用户 ID
        Long noteOwnerId = ntNoteRep.findUserIdById(noteId);
        if (noteOwnerId == null) {
            return PutNoteAvatarEnum.NOTE_NOT_FOUND;
        }

        // 检测笔记是否属于该用户
        if (!noteOwnerId.equals(userId)) {
            return PutNoteAvatarEnum.NOTE_NOT_OWNED_BY_USER;
        }

        // 更新笔记头像
        NoteInfo noteInfo = ntNoteRep.findById(noteId)
                .orElseThrow(() -> new IllegalStateException("笔记已被删除，但检查时未发现"));
        noteInfo.setNoteAvatar(avatar.toCharArray());
        ntNoteRep.save(noteInfo);

        return PutNoteAvatarEnum.UPDATE_SUCCESS;
    }

    @Override
    public PutNoteTitleEnum putNoteTitle(Long userId, Long noteId, String noteTitle) {
        // 检测笔记是否存在并获取其所属用户 ID
        Long noteOwnerId = ntNoteRep.findUserIdById(noteId);
        if (noteOwnerId == null) {
            return PutNoteTitleEnum.NOTE_NOT_FOUND;
        }

        // 检测笔记是否属于该用户
        if (!noteOwnerId.equals(userId)) {
            return PutNoteTitleEnum.NOTE_NOT_OWNED_BY_USER;
        }

        // 更新笔记标题
        NoteInfo noteInfo = ntNoteRep.findById(noteId)
                .orElseThrow(() -> new IllegalStateException("笔记已被删除，但检查时未发现"));
        noteInfo.setNoteTitle(noteTitle); // 设置新标题
        ntNoteRep.save(noteInfo);

        return PutNoteTitleEnum.UPDATE_SUCCESS;
    }

    @Override
    public PutFolderAvatarEnum putFolderAvatar(Long userId, Long folderId, String avatar) {
        // 检测文件夹是否存在并获取其所属用户 ID
        Long folderOwnerId = ntFolderRep.findUserIdById(folderId);
        if (folderOwnerId == null) {
            return PutFolderAvatarEnum.FOLDER_NOT_FOUND;
        }

        // 检测文件夹是否属于该用户
        if (!folderOwnerId.equals(userId)) {
            return PutFolderAvatarEnum.FOLDER_NOT_OWNED_BY_USER;
        }

        // 更新文件夹头像
        FolderInfo folderInfo = ntFolderRep.findById(folderId)
                .orElseThrow(() -> new IllegalStateException("文件夹已被删除，但检查时未发现"));
        folderInfo.setFolderAvatar(avatar.toCharArray()); // 设置新头像
        ntFolderRep.save(folderInfo);

        return PutFolderAvatarEnum.UPDATE_SUCCESS;
    }

    @Override
    public PutFolderTitleEnum putFolderTitle(Long userId, Long folderId, String folderTitle) {
        // 检测文件夹是否存在并获取其所属用户 ID
        Long folderOwnerId = ntFolderRep.findUserIdById(folderId);
        if (folderOwnerId == null) {
            return PutFolderTitleEnum.FOLDER_NOT_FOUND;
        }

        // 检测文件夹是否属于该用户
        if (!folderOwnerId.equals(userId)) {
            return PutFolderTitleEnum.FOLDER_NOT_OWNED_BY_USER;
        }

        // 更新文件夹标题
        FolderInfo folderInfo = ntFolderRep.findById(folderId)
                .orElseThrow(() -> new IllegalStateException("文件夹已被删除，但检查时未发现"));
        folderInfo.setFolderName(folderTitle); // 设置新标题
        ntFolderRep.save(folderInfo);

        return PutFolderTitleEnum.UPDATE_SUCCESS;
    }


}
