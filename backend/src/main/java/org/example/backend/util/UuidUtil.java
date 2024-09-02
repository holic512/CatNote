/**
 * CreateTime: 2024-09-02
 * Description: Uuid的生成工具类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.util;

import java.util.UUID;

public class UuidUtil {
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }
}
