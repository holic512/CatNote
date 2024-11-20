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

import org.example.backend.user.note.noteTree.enums.TreeType;
import org.example.backend.user.note.noteTree.pojo.NoteTreeDto;
import org.example.backend.user.note.noteTree.repository.NTNoteRep;
import org.example.backend.user.note.noteTree.repository.NTFolderRep;
import org.example.backend.user.note.noteTree.service.GNoteTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GNoteTreeServiceImpl implements GNoteTreeService {

    private final NTFolderRep folderRep;
    private final NTNoteRep noteRep;

    @Autowired
    GNoteTreeServiceImpl(NTFolderRep ntFolderRep, NTNoteRep ntNoteRep) {
        this.folderRep = ntFolderRep;
        this.noteRep = ntNoteRep;
    }


    // @Override
    // public List<NoteTreeDto> getNoteTreeList(long userId, Long parentId) {
    //
    //     // 根据 此id 和 此父文件夹id 的 文件夹列表 笔记列表
    //     List<NoteTreeDto> folderList;
    //     List<NoteTreeDto> noteList;
    //     // 当parentId 是null 则执行根目录查询 反之
    //     if (parentId == null) {
    //         folderList = folderRep.findTopLevelFoldersByUserId(userId);
    //         noteList = noteRep.findTopLevelNotesByUserId(userId);
    //     } else {
    //         folderList = folderRep.findSubFoldersByUserIdAndParentId(userId, parentId);
    //         noteList = noteRep.findNotesByUserIdAndFolderId(userId, parentId);
    //     }
    //
    //     // 合并文件夹列表和笔记列表
    //     List<NoteTreeDto> combinedList = new ArrayList<>();
    //     combinedList.addAll(folderList); // 添加文件夹
    //     combinedList.addAll(noteList); // 添加笔记
    //
    //     return combinedList; // 返回合并后的列表
    // }

    @Override
    public Long getFolderIdByNoteId(long NoteId) {
        return noteRep.findFolderIdById(NoteId);
    }


    @Override
    public List<NoteTreeDto> getNoteTreeListByNoteId(long UserId, long ParentId) {
        // 递归查询 - 起点就是 ParentId = 0 (表示根目录) 终点是 此递归 文件夹下 没有 笔记或者文件夹

        // 先查询 根目录的 笔记和文件夹
        List<NoteTreeDto> folderList = folderRep.findFolderByUserIdAndParentId(UserId, ParentId);
        List<NoteTreeDto> noteList = noteRep.findFolderByUserIdAndFolderId(UserId, ParentId);

        // 将查询到的文件夹和笔记合并为一个列表
        List<NoteTreeDto> resultList = new ArrayList<>();
        // 添加文件夹到结果列表
        if (folderList != null && !folderList.isEmpty()) {
            resultList.addAll(folderList);
        }
        // 添加笔记到结果列表
        if (noteList != null && !noteList.isEmpty()) {
            resultList.addAll(noteList);
        }

        // 对每个文件夹进行递归查询，获取其子文件夹和子笔记
        for (NoteTreeDto node : resultList) {
            // 如果是文件夹，则递归查询其子文件夹和笔记
            if (node.getType().equals(TreeType.FOLDER)) {
                // 递归查询当前文件夹的子文件夹和笔记
                List<NoteTreeDto> subTree = getNoteTreeListByNoteId(UserId, node.getId());
                node.setChildren(subTree);  // 将子节点设置为当前文件夹的子节点
            }
        }

        return resultList;
    }
}
