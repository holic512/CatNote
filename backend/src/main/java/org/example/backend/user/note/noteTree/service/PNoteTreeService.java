package org.example.backend.user.note.noteTree.service;

public interface PNoteTreeService {

    void addNote(Long parentId, Long UserId);

    void addFolder(Long parentId, Long UserId);
}
