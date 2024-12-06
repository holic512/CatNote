/**
 * File Name: GFavoriteNoteServiceImpl.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-12-05
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.favoriteNote.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.common.entity.FavoriteNoteInfo;
import org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto;
import org.example.backend.user.note.favoriteNote.repository.FavoriteFolderRep;
import org.example.backend.user.note.favoriteNote.repository.FavoriteNoteRep;
import org.example.backend.user.note.favoriteNote.service.GFavoriteNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GFavoriteNoteServiceImpl implements GFavoriteNoteService {

    private final FavoriteNoteRep favoriteNoteRep;

    private final FavoriteFolderRep favoriteFolderRep;


    @Autowired
    public GFavoriteNoteServiceImpl(FavoriteNoteRep favoriteNoteRep, FavoriteFolderRep favoriteFolderRep) {
        this.favoriteNoteRep = favoriteNoteRep;
        this.favoriteFolderRep = favoriteFolderRep;
    }

    @Override
    public Long getFavoriteFolderIdByNoteId(Long noteId) {
        return favoriteNoteRep.getFavoriteFolderIdByNoteId(noteId);
    }


    @Override
    public List<FavoriteNoteDto> getAllFavoriteNote(Long userId) {
        // 如果 userId 为无效 ID，可以返回空列表
        if (userId == null || userId <= 0) {
            return new ArrayList<>();
        }

        // 查询该用户的所有收藏笔记
        List<FavoriteNoteDto> favoriteNotes = favoriteNoteRep.getAllFavoriteNotesByUserId(userId);

        // 如果没有结果，返回空列表
        return favoriteNotes.isEmpty() ? new ArrayList<>() : favoriteNotes;
    }

    @Override
    public List<FavoriteNoteDto> getAllFavoriteNoteByUserIdAndFavoriteFolderId(Long userId, Long favoriteFolderId) {
        // 如果 userId 为无效 ID，可以返回空列表
        if (userId == null || userId <= 0) {
            return new ArrayList<>();
        }

        List<FavoriteNoteDto> favoriteNotesByUserIdAndFolderId = favoriteNoteRep.getFavoriteNotesByUserIdAndFavoriteFolderId(userId, favoriteFolderId);

        // 如果没有结果，返回空列表
        return favoriteNotesByUserIdAndFolderId.isEmpty() ? new ArrayList<>() : favoriteNotesByUserIdAndFolderId;
    }

    @Override
    public List<FavoriteNoteInfo> getNotesByFolderId(Long folderId) {
        List<FavoriteNoteInfo> notesByFolderId = favoriteNoteRep.getNotesByFolderId(folderId);

        return notesByFolderId.isEmpty() ? Collections.emptyList() : notesByFolderId;
    }

    @Override
    public List<Long> getFolderIdsByUserId(Long userId) {
        return favoriteFolderRep.getFolderIdsByUserId(userId);
    }


}
