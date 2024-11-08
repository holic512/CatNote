/**
 * File Name: ImageStorageServiceTests.java
 * Description: 测试ImageStorageService类的功能，确保图片存储服务正常工作。
 * Author: holic512
 * Created Date: 2024-10-11
 * Version: 1.0
 * Usage:
 * 该测试类用于验证ImageStorageService的实现是否能够正确读取和存储图片。
 */
package org.example.backend;

import org.example.backend.common.ImageStorage.service.ImageStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@SpringBootTest
public class ImageStorageServiceTests {

    private final ImageStorageService imageStorageService;

    @Autowired
    ImageStorageServiceTests(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @Test
    void contextLoads() throws IOException {
        // 保存图片
        String imagePath = "C:\\Users\\holic\\Desktop\\13034c2f48035a9ad529a5c4ad008949.jpg";
        byte[] imageBytes = convertImageToBinary(imagePath);
        imageStorageService.saveImage("ceshi", imageBytes);

        // 测试读取图片 URL
        String imageUrl = imageStorageService.getImageUrl("ceshi.png");
        System.out.println("Image URL: " + imageUrl);

    }

    public static byte[] convertImageToBinary(String imagePath) throws IOException {
        File file = new File(imagePath);
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            return bos.toByteArray();
        }
    }
}
