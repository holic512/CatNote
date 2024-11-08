/**
 * 文件名: ImageNameGenerator.java
 * 描述: 工具类，用于生成随机字符串作为图片名称，通常用于为上传的图片生成唯一名称，以避免名称冲突。
 * 作者: holic512
 * 创建日期: 2024-11-08
 * 版本: 1.0
 * 用法:
 *      - 调用 `generateRandomString()` 方法来获取指定长度的随机字符串。
 */
package org.example.backend.user.note.noteImage.Utils;

import java.security.SecureRandom;

public class ImageNameGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // 图片名称 id 长度
    private static final int LENGTH = 32;

    /**
     * 生成指定长度的随机字符串，用于作为图片的唯一名称。
     *
     * @return 随机生成的图片名称字符串 使用时 后面应该添加类型
     */
    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String randomImageName = generateRandomString();
        System.out.println("生成的图片名称: " + randomImageName);
    }
}
