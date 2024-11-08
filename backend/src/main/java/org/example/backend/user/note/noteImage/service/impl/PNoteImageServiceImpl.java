/**
 * File Name: PNoteImageServiceImpl.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-08
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteImage.service.impl;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.ImageStorage.service.ImageStorageService;
import org.example.backend.user.note.noteImage.Utils.ImageNameGenerator;
import org.example.backend.user.note.noteImage.request.RQImage;
import org.example.backend.user.note.noteImage.service.PNoteImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PNoteImageServiceImpl implements PNoteImageService {

    private ImageStorageService imageStorageService;

    @Autowired
    public PNoteImageServiceImpl(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @Override
    public Pair<Object, Object> saveCompressedNoteImage(String uid, MultipartFile image, String type) {
        try {
            // 获取文件名称
            String imageName = uid + ImageNameGenerator.generateRandomString() + "." + type;


            // 调用保存图片指令
            imageStorageService.saveImage(imageName, image.getBytes());

            return new Pair<>(true, imageStorageService.getImageUrl(imageName));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair<>(false, null);
        }
    }
}
