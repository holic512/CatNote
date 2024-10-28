/**
 * File Name: PNoteTreeServiceImpl.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-10-28
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.noteTree.service.impl;

import jakarta.transaction.Transactional;
import org.example.backend.common.entity.FolderInfo;
import org.example.backend.common.entity.NoteInfo;
import org.example.backend.user.noteTree.pojo.NoteFolderDto;
import org.example.backend.user.noteTree.pojo.NoteTreeDto;
import org.example.backend.user.noteTree.repository.NTFolderRep;
import org.example.backend.user.noteTree.repository.NTNoteRep;
import org.example.backend.user.noteTree.service.PNoteTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PNoteTreeServiceImpl implements PNoteTreeService {

    @Autowired
    private NTNoteRep ntNoteRep;

    @Autowired
    private NTFolderRep ntFolderRep;

    /**
     * 添加笔记
     * @param noteTreeDto 添加的笔记对象
     */
    @Transactional
    @Override
    public NoteInfo addNote(NoteTreeDto noteTreeDto) {

        NoteInfo noteInfo = new NoteInfo();
        noteInfo.setId(noteTreeDto.getId());
        noteInfo.setUserId(noteTreeDto.getUserId());
        noteInfo.setFolderId(noteTreeDto.getFolderId());
        noteInfo.setNoteTitle(noteTreeDto.getNoteTitle());
        noteInfo.setNoteSummary(noteTreeDto.getSummary());
        noteInfo.setNotePassword(noteTreeDto.getNotePassword());
        noteInfo.setNoteType(noteTreeDto.getNoteType());
        noteInfo.setCreatedAt(LocalDateTime.now());
        noteInfo.setUpdatedAt(LocalDateTime.now());


        return ntNoteRep.save(noteInfo);
    }

    @Transactional
    @Override
    public FolderInfo addFolder(NoteFolderDto noteFolderDto) {

        FolderInfo folderInfo = new FolderInfo();
        folderInfo.setUserId(noteFolderDto.getUserId());
        folderInfo.setFolderName(noteFolderDto.getFolderName());
        folderInfo.setDescription(noteFolderDto.getDescription());
        folderInfo.setDescription(noteFolderDto.getDescription());
        folderInfo.setCreatedAt(LocalDateTime.now());
        folderInfo.setUpdatedAt(LocalDateTime.now());

        // 处理parentId，若为null则不设置
        if(folderInfo.getParentId() == null) {
            folderInfo.setParentId(noteFolderDto.getParentId());
        }else{
            folderInfo.setParentId(null);
        }

        return ntFolderRep.save(folderInfo);
    }
}
