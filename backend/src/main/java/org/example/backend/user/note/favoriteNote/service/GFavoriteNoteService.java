package org.example.backend.user.note.favoriteNote.service;

import org.example.backend.common.entity.FavoriteNoteInfo;
import org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GFavoriteNoteService {

    /**
     *  根据收藏笔记ID查询该用户的收藏文件夹ID
     *
     * @param noteId 用户ID
     * @return 用户的所有收藏笔记列表
     */
    Long getFavoriteFolderIdByNoteId(Long noteId);

    /**
     * 根据用户ID查询所有收藏笔记
     *
     * @param userId 用户ID
     * @return 用户所有收藏文件夹的父文件夹ID列表
     */
    List<FavoriteNoteDto> getAllFavoriteNote(Long userId);

    /**
     * 根据用户ID和收藏文件夹ID查询该文件夹下的所有收藏笔记。
     *
     * @param userId         用户ID
     * @param favoriteFolderId 收藏文件夹ID
     * @return 指定文件夹下的所有收藏笔记列表，每个笔记信息封装在 FavoriteNoteDto 对象中
     */
    List<FavoriteNoteDto> getAllFavoriteNoteByUserIdAndFavoriteFolderId(Long userId, Long favoriteFolderId);


    /**
     * 根据收藏文件夹ID查询该文件夹的所有收藏的笔记
     * @param folderId 收藏文件夹ID
     * @return 收藏的笔记列表
     */
    List<FavoriteNoteInfo> getNotesByFolderId(Long folderId);

    /**
     * 根据用户ID查询该用户的所有收藏文件夹ID
     * @param userId 用户ID
     * @return 收藏文件夹ID列表
     */
    List<Long> getFolderIdsByUserId(Long userId);
}
