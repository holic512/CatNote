/**
 * File Name: AdminOUserMmServiceImpl.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-09-23
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.admin.userMm.service.impl;

import org.example.backend.admin.userMm.service.AdminOUserMmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanCursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

@Service
public class AdminOUserMmServiceImpl implements AdminOUserMmService {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        AdminOUserMmServiceImpl.redisTemplate = redisTemplate;
    }

    @Override
    public int fetchOUserCount() {

        int count = 0; // 计数器
        String cursor = "0"; // 初始游标

        do {
            // 执行 SCAN 命令
            ScanOptions scanOptions = ScanOptions.scanOptions()
                    .match("satoken:user:session:*")  // 匹配模式
                    .count(100)
                    .build();
            Cursor<String> scanResult = redisTemplate.scan(scanOptions);

            while (scanResult.hasNext()) {
                scanResult.next(); // 获取当前键
                count++; // 增加计数器
            }

            // 更新游标
            cursor = scanResult.getId().getCursorId(); // 更新游标

        } while (!cursor.equals("0")); // 如果游标为0，表示遍历结束

        return count; // 返回匹配的键的数量

    }
}
