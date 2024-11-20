/**
 * File Name: GUNoteController.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-12
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.note.controller;

import io.lettuce.core.dynamic.annotation.Param;
import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.domain.Note;
import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.note.note.enums.GContextEnum;
import org.example.backend.user.note.note.service.GUNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user/note")
public class GUNoteController {

    private final GUNoteService guNoteService;

    @Autowired
    public GUNoteController(GUNoteService guNoteService) {
        this.guNoteService = guNoteService;
    }

    @GetMapping("context")
    public ResponseEntity<Object> context(@RequestParam Long noteId) {

        // 获取用户Id
        Long UserId = (Long) StpKit.USER.getSession().get("id");

        // 调用服务类
        Pair<GContextEnum, Optional<Note>> result = guNoteService.GetContext(UserId, noteId);

        // 判断状态
        switch (result.a) {
            case Success -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(200)
                        .message("查询成功")
                        .data(result.b)
                        .build());
            }
            case NoteOwnerNotMatch -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(403)
                        .message("查询失败")
                        .data(result.b)
                        .build());
            }
            default -> {
                return ResponseEntity.ok(200);
            }
        }

    }

}
