/**
 * File Name: NoteCoverNoteInfoRep.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-10
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteCover.repository;


import org.example.backend.common.entity.NoteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NoteCoverNoteInfoRep extends JpaRepository<NoteInfo, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE NoteInfo n SET n.note_cover_url = :coverUrl WHERE n.id = :noteId AND n.userId= :userId")
    int updateStatusByIdAndUserId(@Param("userId") Long userId, @Param("noteId") Long noteId, @Param("coverUrl") String coverUrl);
}
