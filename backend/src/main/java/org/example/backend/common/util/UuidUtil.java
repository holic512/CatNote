/**
 * CreateTime: 2024-09-02
 * Description: Uuid的生成工具类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.util;

import java.security.SecureRandom;
import java.util.UUID;

public class UuidUtil {
    private static final SecureRandom random = new SecureRandom();

    /**
     * 生成 乱序的uuid
     *
     * @return uuid
     */
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成 8位 无符号的 uid
     *
     * @return uid
     */
    public static String generateUid() {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10)); // 生成0到9之间的数字
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String uid = generateUid(); // 生成8位数字UID
        System.out.println(uid);
    }
}
