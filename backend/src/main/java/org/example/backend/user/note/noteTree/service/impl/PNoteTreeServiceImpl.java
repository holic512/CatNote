/**
 * File Name: PNoteTreeServiceImpl.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-10-28
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteTree.service.impl;

import org.example.backend.common.entity.FolderInfo;
import org.example.backend.common.entity.NoteInfo;
import org.example.backend.common.enums.note.NoteType;
import org.example.backend.user.note.noteTree.repository.NTFolderRep;
import org.example.backend.user.note.noteTree.repository.NTNoteRep;
import org.example.backend.user.note.noteTree.service.PNoteTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PNoteTreeServiceImpl implements PNoteTreeService {


    private final NTNoteRep ntNoteRep;
    private final NTFolderRep ntFolderRep;

    @Autowired
    PNoteTreeServiceImpl(NTNoteRep ntNoteRep, NTFolderRep ntFolderRep) {
        this.ntNoteRep = ntNoteRep;
        this.ntFolderRep = ntFolderRep;
    }

    @Override
    public void addNote(Long parentId, Long UserId) {
        // 处理数据
        NoteInfo noteInfo = new NoteInfo();
        noteInfo.setNoteTitle("新建笔记");
        noteInfo.setNoteType(NoteType.Normal.getValue());
        noteInfo.setFolderId(parentId);
        noteInfo.setUserId(UserId);

        ntNoteRep.save(noteInfo);
    }

    @Override
    public void addFolder(Long parentId, Long UserId) {

        FolderInfo folderInfo = new FolderInfo();
        folderInfo.setFolderName("新建文件夹");
        folderInfo.setParentId(parentId);
        folderInfo.setUserId(UserId);

        ntFolderRep.save(folderInfo);
    }
}
