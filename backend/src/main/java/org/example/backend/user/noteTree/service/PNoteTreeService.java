package org.example.backend.user.noteTree.service;

import org.example.backend.common.entity.FolderInfo;
import org.example.backend.common.entity.NoteInfo;
import org.example.backend.user.noteTree.pojo.NoteFolderDto;
import org.example.backend.user.noteTree.pojo.NoteTreeDto;

public interface PNoteTreeService {
    /**
     * 添加笔记
     *
     * @param noteTreeDto 添加的笔记对象
     */
    NoteInfo addNote(NoteTreeDto noteTreeDto);

    /**
     * 新增文件夹
     *
     * @param noteFolderDto
     * @return
     */
    FolderInfo addFolder(NoteFolderDto noteFolderDto);
}
