package org.example.backend.user.note.favoriteNote.service;

import org.example.backend.common.entity.FavoriteNoteInfo;
import org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto;

import java.util.List;

public interface PFavoriteNoteService {

    /**
     *  添加收藏
     * @param userId
     * @param noteId
     * @param folderId
     * @return
     */
    void addFavoriteNote(Long userId, Long noteId, Long folderId,String content);

    /**
     *  取消收藏
     * @param userId
     * @param noteId
     */
    void removeFavoriteNote(Long userId, Long noteId);
}
