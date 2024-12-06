/**
 * File Name: PFavoriteNoteServiceImpl.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-12-06
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.favoriteNote.service.impl;

import org.example.backend.common.entity.FavoriteNoteInfo;
import org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto;
import org.example.backend.user.note.favoriteNote.repository.FavoriteNoteRep;
import org.example.backend.user.note.favoriteNote.service.PFavoriteNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PFavoriteNoteServiceImpl implements PFavoriteNoteService {

    private final FavoriteNoteRep favoriteNoteRep;

    @Autowired
    PFavoriteNoteServiceImpl(FavoriteNoteRep favoriteNoteRep) {
        this.favoriteNoteRep = favoriteNoteRep;
    }

    @Override
    public void addFavoriteNote(Long userId, Long noteId, Long folderId,String content) {
        // 判断该笔记是否已在该文件夹下收藏
        FavoriteNoteInfo existingNote = favoriteNoteRep.getByUserIdAndNoteIdAndFavoriteFolderId(userId, noteId, folderId);
        if (existingNote != null) {
            throw new RuntimeException("该笔记已经被收藏在此文件夹中");
        }

        // 创建新的收藏笔记
        FavoriteNoteInfo favoriteNote = new FavoriteNoteInfo();

        favoriteNote.setUserId(userId);
        favoriteNote.setNoteId(noteId);
        favoriteNote.setNoteRemark(content);
        favoriteNote.setFavoriteFolderId(folderId);

        favoriteNoteRep.save(favoriteNote);
    }

    // 取消收藏
    @Override
    public void removeFavoriteNote(Long userId, Long noteId) {
        // 根据用户ID和笔记ID查找已收藏的记录
        FavoriteNoteInfo favoriteNote = favoriteNoteRep.getUserIdAndNoteId(userId, noteId);
        if (favoriteNote == null || !favoriteNote.isFavoriteStatus()) {
            throw new RuntimeException("该笔记未被收藏");
        }

        // 将收藏状态设置为 false，表示取消收藏
        favoriteNote.setFavoriteStatus(false);
        // 查找该用户在该笔记下的收藏记录

        // 删除该记录
        favoriteNoteRep.delete(favoriteNote);

//        // 更新收藏记录
//        favoriteNoteRep.save(favoriteNote);
    }
}
