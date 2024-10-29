/**
 * File Name: GNoteTreeController.java
 * Description: 该控制器用于处理用户笔记树的 GET 请求，主要负责查询用户的文件夹和笔记结构。
 * Author: holic512
 * Created Date: 2024-10-16
 * Version: 1.0
 * Usage:
 * - 通过 `GNoteTreeController` 可以查询用户的文件夹和笔记树结构。
 * - 主要用于前端展示用户的文件夹和笔记列表，支持按父文件夹 ID 进行查询。
 * - 通常在用户界面中展示文件夹树、笔记列表等场景中使用。
 */
package org.example.backend.user.noteTree.controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.noteTree.pojo.NoteFolderDto;
import org.example.backend.user.noteTree.pojo.NoteTreeDto;
import org.example.backend.user.noteTree.service.GNoteTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/noteTree")
public class GNoteTreeController {

    private final GNoteTreeService gNoteTreeService;

    @Autowired
    GNoteTreeController(GNoteTreeService gNoteTreeService) {
        this.gNoteTreeService = gNoteTreeService;
    }

    /**
     * 查询 父级id 为parentID 的文件树列表
     *
     * @param parentId 父级id 为空证明 是父级文件夹
     * @return
     */
    @GetMapping("/noteTree")
    public ResponseEntity<Object> getFolders(@RequestParam(required = false) Long parentId) {
        // 获取用户id
        long id = (long) StpKit.USER.getSession().get("id");

        // 调用服务层
        List<NoteTreeDto> noteTree = gNoteTreeService.getNoteTreeList(id, parentId);

        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("OK")
                .data(noteTree)
                .build()
        );
    }

    @GetMapping("/folderIdByNoteId")
    public ResponseEntity<Object> getFolderIdByNoteId(@RequestParam Long noteId) {

        Long folderId = gNoteTreeService.getFolderIdByNoteId(noteId);

        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("查询成功")
                .data(folderId)
                .build());
    }
}

