/**
 * File Name: ImageController.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-08
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.common.ImageStorage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Value("${image.storage.path}")
    private String storagePath; // 图片存储路径
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    public ImageController() {
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id) {
        logger.info("{}/{}", storagePath, id);

        File file = new File(storagePath, id);
        if (!file.exists()) {
            logger.warn("图片不存在: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("图片不存在: " + id);
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamResource resource = new InputStreamResource(fileInputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // 根据需要设置图片类型
            headers.setContentLength(file.length());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (IOException e) {
            logger.error("读取图片失败: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("读取图片失败");
        }
    }

}
