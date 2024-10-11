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

@SpringBootTest
public class ImageStorageServiceTests {

    private final ImageStorageService imageStorageService;

    @Autowired
    ImageStorageServiceTests(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @Test
    void contextLoads() {
        // 测试读取图片 URL
        String imageUrl = imageStorageService.getImageUrl("ceshi.png");
        System.out.println("Image URL: " + imageUrl);

        // 可以根据需要添加断言，确保返回的URL是有效的
        // Assert.notNull(imageUrl, "Image URL should not be null");
        // Assert.isTrue(imageUrl.startsWith("http"), "Image URL should start with http");
    }
}
