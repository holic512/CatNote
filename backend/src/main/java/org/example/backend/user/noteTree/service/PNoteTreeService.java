package org.example.backend.user.noteTree.service;

import org.example.backend.common.entity.FolderInfo;
import org.example.backend.common.entity.NoteInfo;
import org.example.backend.user.noteTree.pojo.NoteFolderDto;
import org.example.backend.user.noteTree.pojo.NoteTreeDto;

public interface PNoteTreeService {

    void addNote(Long parentId, Long UserId);

    void addFolder(Long parentId, Long UserId);
}
