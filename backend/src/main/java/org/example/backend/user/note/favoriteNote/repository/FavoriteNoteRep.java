package org.example.backend.user.note.favoriteNote.repository;

import org.example.backend.common.entity.FavoriteNoteInfo;
import org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteNoteRep extends JpaRepository<FavoriteNoteInfo, Long> {

    /**
     * 根据收藏文件夹ID查询该文件夹的所有收藏的笔记
     * @param folderId 收藏文件夹ID
     * @return 收藏的笔记列表
     */
    @Query("SELECT n FROM FavoriteNoteInfo n WHERE n.favoriteFolderId = :favoriteFolderId AND n.favoriteStatus = true")
    List<FavoriteNoteInfo> getNotesByFolderId(@Param("favoriteFolderId") Long folderId);

    /**
     * 根据收藏笔记的ID获取收藏文件夹ID。
     *
     * @param id 收藏笔记的ID
     * @return 收藏文件夹的ID
     */
    @Query("SELECT n.favoriteFolderId FROM FavoriteNoteInfo n WHERE n.id = :id")
    Long getFavoriteFolderIdByNoteId(@Param("id") Long id);

    /**
     * 根据用户ID查询该用户所有的收藏笔记。
     *
     * @param userId 用户ID
     * @return 用户的所有收藏笔记列表，每个笔记信息封装在 FavoriteNoteDto 对象中
     */
    @Query("SELECT new org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto(n.id, n.userId, n.noteId, n.favoriteFolderId, " +
            "n.favoriteStatus, n.noteRemark, n.createdAt, n.updatedAt) " +
            "FROM FavoriteNoteInfo n " +
            "WHERE n.userId = :userId")
    List<FavoriteNoteDto> getAllFavoriteNotesByUserId(@Param("userId") Long userId);


    /**
     * 根据用户ID和收藏文件夹ID查询该文件夹下的所有收藏笔记。
     *
     * @param userId         用户ID
     * @param favoriteFolderId 收藏文件夹ID
     * @return 指定文件夹下的所有收藏笔记列表，每个笔记信息封装在 FavoriteNoteDto 对象中
     */
    @Query("SELECT new org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto(n.id, n.userId, n.noteId, n.favoriteFolderId, " +
            "n.favoriteStatus, n.noteRemark, n.createdAt, n.updatedAt) " +
            "FROM FavoriteNoteInfo n " +
            "JOIN NoteInfo no ON n.noteId = no.id " +
            "WHERE n.userId = :userId AND n.favoriteFolderId = :favoriteFolderId")
    List<FavoriteNoteDto> getFavoriteNotesByUserIdAndFavoriteFolderId(@Param("userId") Long userId,
                                                              @Param("favoriteFolderId") Long favoriteFolderId);

    /**
     * 根据用户ID和笔记ID以及收藏文件夹ID查找收藏记录。
     * 用于检查是否已经在指定的文件夹中收藏该笔记。
     *
     * @param userId 用户ID
     * @param noteId 笔记ID
     * @param favoriteFolderId 收藏文件夹ID
     * @return 收藏记录
     */
    @Query("SELECT fn FROM FavoriteNoteInfo fn WHERE fn.userId = :userId AND fn.noteId = :noteId AND fn.favoriteFolderId = :favoriteFolderId")
    FavoriteNoteInfo getByUserIdAndNoteIdAndFavoriteFolderId(@Param("userId") Long userId,
                                                             @Param("noteId") Long noteId,
                                                             @Param("favoriteFolderId") Long favoriteFolderId);

    /**
     * 根据用户ID和笔记ID查找收藏笔记信息
     * @param userId 用户ID
     * @param noteId 笔记ID
     * @return 返回符合条件的收藏笔记记录
     */
    @Query("SELECT f FROM FavoriteNoteInfo f WHERE f.userId = :userId AND f.noteId = :noteId")
    FavoriteNoteInfo getUserIdAndNoteId(@Param("userId") Long userId, @Param("noteId") Long noteId);
}
