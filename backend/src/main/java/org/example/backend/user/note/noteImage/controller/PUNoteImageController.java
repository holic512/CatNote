/**
 * 文件名: PNoteImageController.java
 * 描述: 处理用户笔记图片上传及压缩操作的控制器。
 * 作者: holic512
 * 创建日期: 2024-11-08
 * 版本: 1.0
 * 用法: 该控制器用于处理用户上传图片并压缩的请求，暴露了一个端点 `/user/noteImage/saveCompressedNoteImage`。
 * 用户可以通过此端点上传图片，并选择是否压缩。上传后的图片会被存储，并返回相应的处理结果。
 */

package org.example.backend.user.note.noteImage.controller;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.note.noteImage.service.PUNoteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/noteImage")
public class PUNoteImageController {

    private final PUNoteImageService PUNoteImageService;

    @Autowired
    public PUNoteImageController(PUNoteImageService PUNoteImageService) {
        this.PUNoteImageService = PUNoteImageService;

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
        Pair<Object, Object> result = PUNoteImageService.saveCompressedNoteImage(uid, image, type);

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
