/**
 * File Name: GNoteTreeImpl.java
 * Description: 该类实现了 `GNoteTreeService` 接口，提供了具体的业务逻辑实现，主要用于查询用户的文件夹和笔记信息，并构建笔记树结构。
 * Author: holic512
 * Created Date: 2024-10-16
 * Version: 1.0
 * Usage:
 * - 通过 `GNoteTreeImpl` 类可以查询用户的文件夹和笔记信息。
 * - 通常在控制器层中调用 `getNoteTreeList` 方法，获取文件夹和笔记的层级结构，并返回给前端进行展示。
 * - 前端可以根据返回的 `NoteTreeDto` 列表构建和展示笔记树结构。
 */
package org.example.backend.user.note.noteTree.service.impl;

import org.example.backend.user.note.noteTree.pojo.NoteTreeDto;
import org.example.backend.user.note.noteTree.repository.NTNoteRep;
import org.example.backend.user.note.noteTree.repository.NTFolderRep;
import org.example.backend.user.note.noteTree.service.GNoteTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GNoteTreeImpl implements GNoteTreeService {

    private final NTFolderRep folderRep;
    private final NTNoteRep noteRep;

    @Autowired
    GNoteTreeImpl(NTFolderRep ntFolderRep, NTNoteRep ntNoteRep) {
        this.folderRep = ntFolderRep;
        this.noteRep = ntNoteRep;
    }


    @Override
    public List<NoteTreeDto> getNoteTreeList(long userId, Long parentId) {

        // 根据 此id 和 此父文件夹id 的 文件夹列表 笔记列表
        List<NoteTreeDto> folderList;
        List<NoteTreeDto> noteList;
        // 当parentId 是null 则执行根目录查询 反之
        if (parentId == null) {
            folderList = folderRep.findTopLevelFoldersByUserId(userId);
            noteList = noteRep.findTopLevelNotesByUserId(userId);
        } else {
            folderList = folderRep.findSubFoldersByUserIdAndParentId(userId, parentId);
            noteList = noteRep.findNotesByUserIdAndFolderId(userId, parentId);
        }

        // 合并文件夹列表和笔记列表
        List<NoteTreeDto> combinedList = new ArrayList<>();
        combinedList.addAll(folderList); // 添加文件夹
        combinedList.addAll(noteList); // 添加笔记

        return combinedList; // 返回合并后的列表
    }

    @Override
    public Long getFolderIdByNoteId(long NoteId) {
        return noteRep.findFolderIdById(NoteId);
    }
}
