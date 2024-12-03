/**
 * File Name: LocalImageStorageService.java
 * Description: 本地图片存储服务实现
 * Author: holic512
 * Created Date: 2024-09-23
 * Version: 1.0
 * Usage: 提供将图片保存到本地文件系统、删除图片、获取图片和生成图片访问 URL 的功能。
 */
package org.example.backend.common.ImageStorage.service.impl.Local;

import org.example.backend.common.ImageStorage.service.ImageStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.file.Files;

public class LocalImageStorageService implements ImageStorageService {

    private static final Logger logger = LoggerFactory.getLogger(LocalImageStorageService.class);

    @Value("${image.storage.path}")
    private String storagePath;

    public LocalImageStorageService() {
    }

    @Override
    public void saveImage(String imageName, byte[] image) {
        if (image == null || image.length == 0) {
            throw new IllegalArgumentException("图片不能为空");
        }

        File file = new File(storagePath, imageName);
        if (file.exists()) {
            logger.warn("文件已存在，保存失败");
            throw new RuntimeException("文件已存在，保存失败");
        }

        try (InputStream inputStream = new ByteArrayInputStream(image);
             FileOutputStream fos = new FileOutputStream(file)) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) > 0) {
                fos.write(bytes, 0, length);
            }
            logger.info("成功保存图片到本地: {}", file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("保存图片失败: {}", e.getMessage());
            throw new RuntimeException("保存图片失败", e);
        }
    }

    @Override
    public void deleteImage(String id) {
        File file = new File(storagePath, id);

        if (!file.exists()) {
            logger.warn("文件不存在，删除失败:{}", file.getAbsolutePath());
            throw new RuntimeException("文件不存在，无法删除");
        }
        if (file.delete()) {
            logger.info("成功删除图片: {}", file.getAbsolutePath());
        } else {
            logger.warn("删除图片失败: {}", file.getAbsolutePath());
            throw new RuntimeException("删除图片失败");
        }
    }

    @Override
    public byte[] getImage(String id) {
        File file = new File(storagePath, id);

        if (!file.exists()) {
            logger.warn("文件不存在:{}", file.getAbsolutePath());
            throw new RuntimeException("文件不存在");
        }

        try {
            byte[] imageData = Files.readAllBytes(file.toPath());
            logger.info("成功获取图片: {}", file.getAbsolutePath());
            return imageData;
        } catch (IOException e) {
            logger.error("获取图片失败: {}", e.getMessage());
            throw new RuntimeException("获取图片失败", e);
        }
    }

    @Override
    public String getImageUrl(String imageName) {
        // 这里可以根据需要返回本地图片的 URL
        File file = new File(storagePath, imageName);
        if (!file.exists()) {
            // 图片不存在 传递空地址
            return null;
        }

        return "http://localhost:8080/images/" + imageName;
    }

}
