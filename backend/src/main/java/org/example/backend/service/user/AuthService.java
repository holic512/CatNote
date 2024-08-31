/**
 * CreateTime: 2024-08-29
 * Description: 权限获取服务类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.service.user;

import jakarta.mail.MessagingException;
import org.example.backend.Dto.user.AuthDto;
import org.example.backend.Repository.UserRepository;
import org.example.backend.enums.UserRole;
import org.example.backend.enums.VerificationCodePurpose;
import org.example.backend.util.JwtUtil;
import org.example.backend.util.MailUtil;
import org.example.backend.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AuthService {
    // 通过构造函数注入
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final MailUtil mailUtil;

    @Autowired
    public AuthService(UserRepository userRepository, RedisTemplate<String, Object> redisTemplate, MailUtil mailUtil) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.mailUtil = mailUtil;
    }


    /**
     * 账号密码登录 服务层
     *
     * @param username 用户名
     * @param password 密码
     * @return 由 uid 用户权限组成 token
     */
    public String PLogin(String username, String password) {
        final AuthDto authDto = userRepository.findAuthDtoByUsername(username);

        if (authDto == null) {
            return "-1"; // 用户未找到
        }

        if (!password.equals(authDto.getPassword())) {
            return "-2"; // 密码不匹配
        }

        return JwtUtil.generateToken(authDto.getUid(), UserRole.USER);
    }


    /**
     * 登录验证码的邮件发送,数据存储 服务层
     *
     * @param email 邮箱地址
     * @throws MessagingException 邮件错误
     */
    public boolean sendLoginCode(String email) throws MessagingException {

        // 键名 与 过期时间(分钟)
        final String keyName = "CodeNote:user:login:";
        final int timeout = 5;

        // 判断邮箱是否存在
        final boolean exists = userRepository.existsByEmail(email);
        if (!exists) {
            return false;
        }

        // 获取验证码
        String code = VerificationCodeUtil.generateVerificationCode();

        // 将验证码放入Redis
        redisTemplate.opsForValue().set(keyName + email, code, timeout, TimeUnit.MINUTES);

        // 将验证码发送邮箱
        mailUtil.sendVerificationCode(email, code, VerificationCodePurpose.UserLogin);

        return true;

    }

    /**
     * 验证 用于登录的验证码 服务层
     *
     * @param email 邮箱地址
     * @param code  验证码
     * @return token
     */
    public String verifyLoginCode(String email, String code) {
        
        final String keyName = "CodeNote:user:login:";

        // 查询该键的value
        String value = (String) redisTemplate.opsForValue().get(keyName + email);

        // 表示没有这个请求,或验证码不正确
        if (value == null || !value.equals(code)) {
            return null;
        }

        // 移除已验证的验证码,确定用户uid并返回token
        redisTemplate.delete(keyName + email);
        final AuthDto authDto = userRepository.findAuthDtoByEmail(email);
        return JwtUtil.generateToken(authDto.getUid(), UserRole.USER);

    }
}
