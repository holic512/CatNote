/**
 * File Name: PNoteImageController.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-08
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteImage.controller;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.note.noteImage.request.RQImage;
import org.example.backend.user.note.noteImage.service.PNoteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/noteImage")
public class PNoteImageController {

    private final PNoteImageService pNoteImageService;

    @Autowired
    public PNoteImageController(PNoteImageService pNoteImageService) {
        this.pNoteImageService = pNoteImageService;

    }

    // 保存图片并且压缩格式
    @PostMapping("saveCompressedNoteImage")
    public ResponseEntity<Object> saveCompressedNoteImage(
            @RequestParam("type") String type,
            @RequestParam("image") MultipartFile image
    ) {
        // 获取用户uid
        String uid = (String) StpKit.USER.getLoginId();

        // 调用服务层
        Pair<Object, Object> result = pNoteImageService.saveCompressedNoteImage(uid, image,type);

        // 判断返回状态,并返回响应体
        boolean status = (boolean) result.a;
        if (status) {
            return ResponseEntity.ok(new ApiResponse.Builder<Object>()
                    .status(200)
                    .message("插入图片成功")
                    .data(result.b)
                    .build()
            );
        } else {
            return ResponseEntity.ok(new ApiResponse.Builder<Object>()
                    .status(404)
                    .build()
            );
        }
    }


}
