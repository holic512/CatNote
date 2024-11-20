/**
 * File Name: PutNoteTreeController.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-20
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteTree.controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.note.noteTree.enums.PutFolderTitleEnum;
import org.example.backend.user.note.noteTree.enums.PutNoteAvatarEnum;
import org.example.backend.user.note.noteTree.enums.PutFolderAvatarEnum;
import org.example.backend.user.note.noteTree.enums.PutNoteTitleEnum;
import org.example.backend.user.note.noteTree.service.PutNoteTreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user/noteTree")
public class PutNoteTreeController {

    private final PutNoteTreeService putNoteTreeService;

    public PutNoteTreeController(PutNoteTreeService putNoteTreeService) {
        this.putNoteTreeService = putNoteTreeService;

    }

    @PutMapping("NoteAvatar")
    public ResponseEntity<Object> putAvatar(@RequestBody HashMap<String, String> noteData) {
        // 获取user id
        long UserId = (long) StpKit.USER.getSession().get("id");

        // 获取前端数据
        long noteId = Long.parseLong(noteData.get("noteId"));
        String avatar = noteData.get("avatar");

        // 调用服务层  -判断是否存在该笔记-> 判断 笔记是否属于此用户 -> 修改此笔记的头像
        PutNoteAvatarEnum putNoteAvatarEnum = putNoteTreeService.putNoteAvatar(UserId, noteId, avatar);

        // 条件判断
        switch (putNoteAvatarEnum) {
            case NOTE_NOT_FOUND -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(404)
                        .message("该笔记不存在")
                        .build()
                );
            }
            case NOTE_NOT_OWNED_BY_USER -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(401)
                        .message("该笔记不属于当前用户")
                        .build()
                );
            }
            case UPDATE_SUCCESS -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(200)
                        .message("修改成功")
                        .build()
                );
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(500)
                        .message("未知错误")
                        .build()
                );
            }
        }

    }

    @PutMapping("NoteTitle")
    public ResponseEntity<Object> putNoteTitle(@RequestBody HashMap<String, String> noteData) {
        // 获取用户 ID
        long userId = (long) StpKit.USER.getSession().get("id");

        // 获取前端数据
        long noteId = Long.parseLong(noteData.get("noteId"));
        String noteTitle = noteData.get("noteTitle");

        // 调用服务层 - 判断是否存在该笔记 -> 判断笔记是否属于此用户 -> 修改笔记标题
        PutNoteTitleEnum putNoteTitleEnum = putNoteTreeService.putNoteTitle(userId, noteId, noteTitle);

        // 条件判断
        switch (putNoteTitleEnum) {
            case NOTE_NOT_FOUND -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(404)
                        .message("该笔记不存在")
                        .build()
                );
            }
            case NOTE_NOT_OWNED_BY_USER -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(401)
                        .message("该笔记不属于当前用户")
                        .build()
                );
            }
            case UPDATE_SUCCESS -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(200)
                        .message("标题修改成功")
                        .build()
                );
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(500)
                        .message("未知错误")
                        .build()
                );
            }
        }
    }

    @PutMapping("FolderAvatar")
    public ResponseEntity<Object> putFolderAvatar(@RequestBody HashMap<String, String> folderData) {
        // 获取用户 ID
        long userId = (long) StpKit.USER.getSession().get("id");

        // 获取前端数据
        long folderId = Long.parseLong(folderData.get("folderId"));
        String avatar = folderData.get("avatar");

        // 调用服务层 - 检查文件夹是否存在 -> 判断文件夹是否属于该用户 -> 修改文件夹头像
        PutFolderAvatarEnum putFolderAvatarEnum = putNoteTreeService.putFolderAvatar(userId, folderId, avatar);

        // 条件判断
        switch (putFolderAvatarEnum) {
            case FOLDER_NOT_FOUND -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(404)
                        .message("该文件夹不存在")
                        .build()
                );
            }
            case FOLDER_NOT_OWNED_BY_USER -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(401)
                        .message("该文件夹不属于当前用户")
                        .build()
                );
            }
            case UPDATE_SUCCESS -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(200)
                        .message("文件夹头像修改成功")
                        .build()
                );
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(500)
                        .message("未知错误")
                        .build()
                );
            }
        }
    }

    @PutMapping("FolderTitle")
    public ResponseEntity<Object> putFolderTitle(@RequestBody HashMap<String, String> folderData) {
        // 获取用户 ID
        long userId = (long) StpKit.USER.getSession().get("id");

        // 获取前端数据
        long folderId = Long.parseLong(folderData.get("folderId"));
        String folderTitle = folderData.get("folderTitle");

        // 调用服务层 - 检查文件夹是否存在 -> 判断文件夹是否属于该用户 -> 修改文件夹标题
        PutFolderTitleEnum putFolderTitleEnum = putNoteTreeService.putFolderTitle(userId, folderId, folderTitle);

        // 条件判断
        switch (putFolderTitleEnum) {
            case FOLDER_NOT_FOUND -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(404)
                        .message("该文件夹不存在")
                        .build()
                );
            }
            case FOLDER_NOT_OWNED_BY_USER -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(401)
                        .message("该文件夹不属于当前用户")
                        .build()
                );
            }
            case UPDATE_SUCCESS -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(200)
                        .message("文件夹标题修改成功")
                        .build()
                );
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(500)
                        .message("未知错误")
                        .build()
                );
            }
        }
    }
}
