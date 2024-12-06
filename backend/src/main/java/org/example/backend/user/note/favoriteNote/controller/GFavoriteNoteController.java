/**
 * File Name: GFavoriteNoteController.java
 * Description: Todo
 * Author: lv
 * Created Date: 2024-12-05
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.favoriteNote.controller;

import org.example.backend.common.entity.FavoriteNoteInfo;
import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.note.favoriteNote.pojo.FavoriteNoteDto;
import org.example.backend.user.note.favoriteNote.service.GFavoriteNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/favorite")
public class GFavoriteNoteController {

    private final GFavoriteNoteService GfavoriteNoteService;

    @Autowired
    GFavoriteNoteController(final GFavoriteNoteService favoriteNoteService) {
        this.GfavoriteNoteService = favoriteNoteService;
    }

    @GetMapping("/favoriteFolderIdByNoteId")
    public ResponseEntity<Object> getFavoriteFolderIdByFavoriteNoteId(@RequestParam Long favoriteNoteId) {
        Long FavoriteFolderId = GfavoriteNoteService.getFavoriteFolderIdByNoteId(favoriteNoteId);

        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("查询成功")
                .data(FavoriteFolderId)
                .build()
        );
    }

    @GetMapping("/favoriteNotes")
    public ResponseEntity<Object> getAllFavoriteNotes() {
        Long userId = (Long) StpKit.USER.getSession().get("id");

        if (userId == null || userId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse.Builder<>()
                            .status(400)
                            .message("无效的用户ID")
                            .build());
        }

        // 获取所有收藏笔记
        List<FavoriteNoteDto> favoriteNotes = GfavoriteNoteService.getAllFavoriteNote(userId);

        if (!favoriteNotes.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse.Builder<>()
                    .status(200)
                    .message("查询成功")
                    .data(favoriteNotes)
                    .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse.Builder<>()
                    .status(404)
                    .message("未找到相关的收藏笔记")
                    .build());
        }
    }




    @GetMapping("/getNoteByFFolderId")
    public ResponseEntity<Object> getFavoriteNoteByUserIdAndFavoriteFolderId(@RequestParam Long UserId, @RequestParam Long favoriteFolderId) {
        List<FavoriteNoteDto> allFavoriteNoteByUserIdAndFavoriteFolderId = GfavoriteNoteService.getAllFavoriteNoteByUserIdAndFavoriteFolderId(UserId, favoriteFolderId);

        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("查询成功")
                .data(allFavoriteNoteByUserIdAndFavoriteFolderId)
                .build()
        );
    }

    @GetMapping("/getNotesByFFolderId")
    public ResponseEntity<Object> getNotesByFolderId(@RequestParam Long folderId) {
        List<FavoriteNoteInfo> notes = GfavoriteNoteService.getNotesByFolderId(folderId);

        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("查询成功")
                .data(notes)
                .build()
        );
    }

    @GetMapping("/getFFolderIdByUserId")
    public ResponseEntity<Object> getFavoriteFolderIdByUserId(@RequestParam Long userId) {
        List<Long> notes = GfavoriteNoteService.getFolderIdsByUserId(userId);

        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("查询成功")
                .data(notes)
                .build()
        );
    }

}
