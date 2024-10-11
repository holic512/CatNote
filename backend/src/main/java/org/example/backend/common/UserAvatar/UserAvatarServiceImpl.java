/**
 * File Name: UserAvatarServiceImpl.java
 * Description: 用户头像服务实现类，负责处理用户头像的上传和获取。
 * Author: holic512
 * Created Date: 2024-10-11
 * Version: 1.0
 * Usage: 该类实现了UserAvatarService接口，提供头像的上传和URL获取功能。
 */
package org.example.backend.common.UserAvatar;

import org.example.backend.common.ImageStorage.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

@Service
public class UserAvatarServiceImpl implements UserAvatarService {

    private ImageStorageService imageStorageService;

    // 头像分辨率
    int targetWidth = 256;
    int targetHeight = 256;

    @Autowired
    public void setImageStorageService(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @Override
    public String updateUserAvatar(String userId, MultipartFile avatarFile) throws Exception {
        // 获取文件的字节数组并将其转换为 BufferedImage
        BufferedImage originalImage = ImageIO.read(avatarFile.getInputStream());

        // 压缩头像文件
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        // 将 BufferedImage 转回字节数组
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos);
        byte[] compressedImageBytes = baos.toByteArray();

        // 保存头像文件到 ImageStorageService
        imageStorageService.saveImage("avatar_" + userId + ".jpg", compressedImageBytes);

        // 返回新头像的 URL
        return imageStorageService.getImageUrl("avatar_" + userId + ".jpg");
    }

    @Override
    public String getUserAvatarUrl(Long userId) {
        // 获取用户头像的URL
        return imageStorageService.getImageUrl("avatar_" + userId + ".jpg");
    }
}
