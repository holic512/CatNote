package org.example.backend.user.note.favoriteNote.repository;

import org.example.backend.common.entity.FavoriteFolderInfo;
import org.example.backend.common.entity.FavoriteNoteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteFolderRep extends JpaRepository<FavoriteFolderInfo, Long> {

    /**
     * 根据用户ID查询该用户的所有收藏文件夹ID
     * @param userId 用户ID
     * @return 收藏文件夹ID列表
     */
    @Query("SELECT f.id FROM FavoriteFolderInfo f WHERE f.userId = :userId")
    List<Long> getFolderIdsByUserId(Long userId);
}

