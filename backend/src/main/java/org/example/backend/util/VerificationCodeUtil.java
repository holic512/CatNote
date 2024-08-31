/**
 * CreateTime: 2024-08-29
 * Description: 用作发送邮件
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class VerificationCodeUtil {


    // 随机对象 与 代码长度
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int CODE_LENGTH = 6;

    /**
     * 生成一个6位数字验证码
     *
     * @return 6位数字验证码的字符串表示
     */
    public static String generateVerificationCode() {
        int min = (int) Math.pow(10, CODE_LENGTH - 1); // 100000
        int max = (int) Math.pow(10, CODE_LENGTH) - 1; // 999999
        int code = secureRandom.nextInt((max - min) + 1) + min;
        return String.valueOf(code);
    }


    // /**
    //  * 参数: 邮箱
    //  * 功能: 获取 用户用于获取权限的参数信息 从Redis
    //  */
    // public Optional<String> getUserSignRedis(String email) {
    //     return Optional.ofNullable((String) redisTemplate.opsForValue().get(keyName + email));
    //
    // }
}
