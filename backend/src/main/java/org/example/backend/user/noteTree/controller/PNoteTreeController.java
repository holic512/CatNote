/**
 * File Name: PNoteTreeController.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-10-28
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.noteTree.controller;

import org.apache.coyote.Response;
import org.example.backend.common.entity.FolderInfo;
import org.example.backend.common.entity.NoteInfo;
import org.example.backend.common.response.ApiResponse;
import org.example.backend.user.noteTree.pojo.NoteFolderDto;
import org.example.backend.user.noteTree.pojo.NoteTreeDto;
import org.example.backend.user.noteTree.service.GNoteTreeService;
import org.example.backend.user.noteTree.service.PNoteTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/noteTree")
public class PNoteTreeController {

    @Autowired
    private PNoteTreeService pNoteTreeService;


    @PostMapping("/note")
    public ResponseEntity<ApiResponse<NoteInfo>> addNotes(@RequestBody NoteTreeDto noteTreeDto) {
        NoteInfo addNote = pNoteTreeService.addNote(noteTreeDto);

        return ResponseEntity.ok(new ApiResponse.Builder<NoteInfo>()
                .status(200)
                .message("添加笔记成功")
                .data(addNote)
                .build());
    }

    @PostMapping("/folder")
    public ResponseEntity<ApiResponse<FolderInfo>> addFolder(@RequestBody NoteFolderDto noteFolderDto) {
        FolderInfo folder = pNoteTreeService.addFolder(noteFolderDto);

        return ResponseEntity.ok(new ApiResponse.Builder<FolderInfo>()
                .status(200)
                .message("添加文件夹成功")
                .data(folder)
                .build());
    }
}
