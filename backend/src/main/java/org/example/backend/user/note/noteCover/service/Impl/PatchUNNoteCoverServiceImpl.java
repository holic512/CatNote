/**
 * File Name: PostUNNoteCoverServiceImpl.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-10
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteCover.service.Impl;

import org.example.backend.user.note.noteCover.enums.PatchContextEnum;
import org.example.backend.user.note.noteCover.repository.NoteCoverNoteInfoRep;
import org.example.backend.user.note.noteCover.service.PatchUNNoteCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatchUNNoteCoverServiceImpl implements PatchUNNoteCoverService {

    private final NoteCoverNoteInfoRep noteInfoRep;

    @Autowired
    public PatchUNNoteCoverServiceImpl(NoteCoverNoteInfoRep noteInfoRep) {
        this.noteInfoRep = noteInfoRep;
    }

    @Override
    public PatchContextEnum UpNoteCover(Long userId, Long noteId, String coverUrl) {
        int count = noteInfoRep.updateStatusByIdAndUserId(userId, noteId, coverUrl);
        if (count > 0) {
            return PatchContextEnum.SUCCESS;
        } else {
            return PatchContextEnum.FAIl;
        }

    }
}
