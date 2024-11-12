/**
 * File Name: GUNoteService.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-12
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.note.service.impl;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.domain.Note;
import org.example.backend.user.note.note.enums.GContextEnum;
import org.example.backend.user.note.note.repository.UNoteInfoRep;
import org.example.backend.user.note.note.repository.UNoteRepM;
import org.example.backend.user.note.note.service.GUNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GUNoteServiceImpl implements GUNoteService {

    private final UNoteInfoRep uNoteInfoRep;
    private final UNoteRepM uNoteRepM;

    @Autowired
    public GUNoteServiceImpl(UNoteInfoRep uNoteInfoRep, UNoteRepM uNoteRepM) {
        this.uNoteInfoRep = uNoteInfoRep;
        this.uNoteRepM = uNoteRepM;
    }

    @Override
    public Pair<GContextEnum, Optional<Note>> GetContext(Long userId, Long noteId) {
        // 根据笔记Id 查询用户Id
        Long ownerId = uNoteInfoRep.findUserIdByNoteId(noteId);

        if (!ownerId.equals(userId)) {
            return new Pair<>(GContextEnum.NoteOwnerNotMatch, null);
        }

        Optional<Note> note = uNoteRepM.findById(noteId);
        return new Pair<>(GContextEnum.Success, note);
    }
}
