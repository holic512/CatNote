/**
 * File Name: PostUNNoteCoverController.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-10
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteCover.Controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.note.noteCover.enums.PatchContextEnum;
import org.example.backend.user.note.noteCover.service.PatchUNNoteCoverService;
import org.example.backend.user.todo.enums.PatchUTContextEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("user/noteCover")
public class PatchUNNoteCoverController {

    private final PatchUNNoteCoverService patchService;

    @Autowired
    public PatchUNNoteCoverController(PatchUNNoteCoverService patchService) {
        this.patchService = patchService;
    }

    @PatchMapping("NoteCover")
    public ResponseEntity<Object> UpNoteCover(@RequestBody HashMap<String, Object> request) {
        // 获取用户Id
        Long UserId = (Long) StpKit.USER.getSession().get("id");

        // 获取用户数据
        Long NoteId = ((Integer) request.get("noteId")).longValue();
        String NoteCover = (String) request.get("noteCover");

        PatchContextEnum result = patchService.UpNoteCover(UserId, NoteId, NoteCover);

        if (result == PatchContextEnum.SUCCESS) {
            return ResponseEntity.ok(new ApiResponse.Builder<>()
                    .status(200)
                    .message("更新成功")
                    .build()
            );
        }
        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(500)
                .message("更新失败")
                .build()
        );

    }
}
