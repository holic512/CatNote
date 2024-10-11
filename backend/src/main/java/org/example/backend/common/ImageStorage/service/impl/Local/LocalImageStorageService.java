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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LocalImageStorageService implements ImageStorageService {

    private static final Logger logger = LoggerFactory.getLogger(LocalImageStorageService.class);
    private final String storagePath;

    public LocalImageStorageService() {
        this.storagePath = "your/local/storage/path"; // 指定本地存储路径
        System.out.println("LocalImageStorageService initialized with path: " + storagePath);
    }

    @Override
    public void saveImage(String id, byte[] image) {
        File file = new File(storagePath, id);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(image);
            logger.info("成功保存图片到本地: {}", file.getAbsolutePath());
        } catch (IOException e) {
            logger.error("保存图片失败: {}", e.getMessage());
            throw new RuntimeException("保存图片失败", e);
        }
    }

    @Override
    public void deleteImage(String id) {
        File file = new File(storagePath, id);
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
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] imageData = new byte[(int) file.length()];
            fis.read(imageData);
            logger.info("成功获取图片: {}", file.getAbsolutePath());
            return imageData;
        } catch (IOException e) {
            logger.error("获取图片失败: {}", e.getMessage());
            throw new RuntimeException("获取图片失败", e);
        }
    }

    @Override
    public String getImageUrl(String id) {
        // 这里可以根据需要返回本地图片的 URL
        File file = new File(storagePath, id);
        if (file.exists()) {
            return "file:///" + file.getAbsolutePath(); // 返回本地文件的路径
        } else {
            throw new RuntimeException("图片不存在: " + id);
        }
    }
}
