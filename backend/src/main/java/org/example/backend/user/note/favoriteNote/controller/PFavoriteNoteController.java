/**
 * File Name: PFavoriteNoteController.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-12-06
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.favoriteNote.controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto;
import org.example.backend.user.note.favoriteNote.service.PFavoriteNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/favorite")
public class PFavoriteNoteController {

    private final PFavoriteNoteService favoriteNoteService;

    @Autowired
    public PFavoriteNoteController(PFavoriteNoteService favoriteNoteService) {
        this.favoriteNoteService = favoriteNoteService;
    }

    /**
     * 添加收藏笔记
     * @param userId 用户ID
     * @param noteId 笔记ID
     * @param folderId 收藏文件夹ID
     * @return ResponseEntity 返回操作结果
     */
    @PostMapping("/add")
    public ResponseEntity<Object> addFavoriteNote(@RequestParam(value = "userId", required = false) Long userId,
                                                  @RequestParam(value = "noteId", required = false) Long noteId,
                                                  @RequestParam(value = "folderId", required = false) Long folderId,
                                                  @RequestParam(value = "noteRemark", required = false) String content) {

        // 非空判断
        if (userId == null || noteId == null || folderId == null) {
            return ResponseEntity.badRequest().body("用户ID、笔记ID和收藏夹ID不能为空");
        }
//        "笔记已成功添加到收藏夹"
        try {
            favoriteNoteService.addFavoriteNote(userId, noteId, folderId,content);
            return ResponseEntity.ok(new ApiResponse.Builder<>()
                    .status(200)
                    .message("笔记已成功添加到收藏夹")
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 取消收藏
     * @param userId 用户ID
     * @param noteId 笔记ID
     * @return 响应消息
     */
    @PostMapping("/removeFavoriteNote")
    public ResponseEntity<Object> removeFavoriteNote(@RequestParam Long userId,
                                                     @RequestParam Long noteId) {

        try {
            favoriteNoteService.removeFavoriteNote(userId, noteId);
            return ResponseEntity.ok(new ApiResponse.Builder<>()
                    .status(200)
                    .message("取消收藏成功")
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("取消收藏失败: " + e.getMessage());
        }
    }
}
