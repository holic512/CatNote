/**
 * File Name: LocalImageStorageService.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-09-23
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.common.service.ImageStorage.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.backend.common.service.ImageStorage.ImageStorageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Slf4j
public class LocalImageStorageService implements ImageStorageService {
    private final String storageDirectory;

    // 构造函数，设置存储目录
    public LocalImageStorageService(String storageDirectory) {
        this.storageDirectory = storageDirectory;
        // 创建存储目录（如果不存在）
        File dir = new File(storageDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public void saveImage(String id, byte[] image) {
        File file = new File(storageDirectory, id);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(image);
        } catch (IOException e) {
            log.error(e.getMessage());
            // 处理异常（可以选择抛出自定义异常）
        }
    }

    @Override
    public void deleteImage(String id) {
        File file = new File(storageDirectory, id);
        if (file.exists()) {
            if (!file.delete()) {
                // 处理删除失败的情况
                log.error("Could not delete file {}", file.getAbsolutePath());
            }
        } else {
            // 文件不存在的处理
            System.err.println("文件不存在: " + id);
        }
    }

    @Override
    public byte[] getImage(String id) {
        File file = new File(storageDirectory, id);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] imageData = new byte[(int) file.length()];
                fis.read(imageData);
                return imageData;
            } catch (IOException e) {
                log.error(e.getMessage());
                // 处理读取异常
            }
        }
        return null; // 文件不存在或读取失败时返回 null
    }

    @Override
    public String getImageUrl(String id) {
        File file = new File(storageDirectory, id);
        if (file.exists()) {
            // 生成文件的本地路径，可以根据需要返回 HTTP URL 等
            return Paths.get(storageDirectory, id).toUri().toString();
        }
        return null; // 文件不存在时返回 null
    }


    public static void main(String[] args) {
        
    }
}
