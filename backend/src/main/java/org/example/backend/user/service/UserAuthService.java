/**
 * CreateTime: 2024-08-29
 * Description: 权限获取服务类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.dto.MailCodeMessage;
import org.example.backend.common.util.JwtUtil;
import org.example.backend.common.util.SCryptUtil;
import org.example.backend.common.util.UuidUtil;
import org.example.backend.common.util.VerificationCodeUtil;
import org.example.backend.common.enums.MQExchangeType;
import org.example.backend.common.enums.MQRoutingKey;
import org.example.backend.common.enums.UserRole;
import org.example.backend.common.enums.MailCodePurpose;
import org.example.backend.user.enums.AuthServiceEnum;
import org.example.backend.user.dto.AuthDto;
import org.example.backend.user.repository.UserRepository;
import org.example.backend.user.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserAuthService {
    // 通过构造函数注入
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserAuthService(UserRepository userRepository, RedisTemplate<String, Object> redisTemplate, RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;

        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    // 定义注册行为的时效性
    final int timeout = 5;
    final String keyRegPend = "CodeNote:user:register:pending:";
    final String keyReg = "CodeNote:user:register:";


    /**
     * 账号密码登录 服务层
     *
     * @param username 用户名
     * @param password 密码
     * @return 由 uid 用户权限组成 token
     */
    public Pair<AuthServiceEnum, String> PLogin(String username, String password) {
        final AuthDto authDto = userRepository.findAuthDtoByUsername(username);

        if (authDto == null) {
            return new Pair<>(AuthServiceEnum.UserNotFound, null); // 用户未找到
        }


        if (!SCryptUtil.verifyPassword(password, authDto.getPassword())) {
            return new Pair<>(AuthServiceEnum.INCORRECT, null); // 密码不匹配
        }

        String token = JwtUtil.generateToken(authDto.getUid(), UserRole.USER);
        return new Pair<>(AuthServiceEnum.Success, token);
    }


    /**
     * 登录验证码的邮件发送,数据存储 服务层
     *
     * @param email 邮箱地址
     */
    public boolean sendLoginCode(String email) throws JsonProcessingException {

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


        // 将验证码发送邮箱 - 添加队列
        MailCodeMessage mailCodeMessage = new MailCodeMessage(email, code, MailCodePurpose.UserLogin);
        String message = objectMapper.writeValueAsString(mailCodeMessage);
        rabbitTemplate.convertAndSend(MQExchangeType.DIRECT_EXCHANGE.getValue(), MQRoutingKey.EMAIL_ROUTING_KEY.getKey(), message);

        return true;

    }

    /**
     * 验证 登录验证码 的 服务层
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

    /**
     * 发送 注册请求
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     * @return 是否成功添加请求
     */
    public Pair<AuthServiceEnum, String> initiateReg(String username, String password, String email) throws JsonProcessingException {


        // 检测 用户名和邮箱 是否被创建
        if (userRepository.existsByUsername(username)) {
            return new Pair<>(AuthServiceEnum.UserAlreadyExists, null);
        }
        if (userRepository.existsByEmail(email)) {
            return new Pair<>(AuthServiceEnum.EmailAlreadyExists, null);
        }


        // 验证待注册区内是否有 此用户名 或者 邮箱 的请求 -> 存在则删除原先,以用来覆盖
        boolean exUser = Boolean.TRUE.equals(redisTemplate.hasKey(keyRegPend + username));
        boolean exEmail = Boolean.TRUE.equals(redisTemplate.hasKey(keyRegPend + email));

        if (exUser) {
            String ex1 = (String) redisTemplate.opsForValue().get(keyRegPend + username);
            redisTemplate.delete(keyRegPend + username);
            if (ex1 != null) {
                redisTemplate.delete(keyReg + ex1);
            }
        }
        if (exEmail) {
            String ex2 = (String) redisTemplate.opsForValue().get(keyRegPend + email);
            redisTemplate.delete(keyRegPend + email);
            if (ex2 != null) {
                redisTemplate.delete(keyReg + ex2);
            }
        }


        // 生成用于标识注册对话行为的id 以及 验证码
        String regID = UuidUtil.getUuid();
        String code = VerificationCodeUtil.generateVerificationCode();

        // 将此用户名,邮箱 添加到 待注册区
        redisTemplate.opsForValue().set(keyRegPend + username, regID, timeout, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(keyRegPend + email, regID, timeout, TimeUnit.MINUTES);

        // 将regID:邮箱,用户名,密码,验证码 添加到 注册区
        Map<String, String> registerData = new HashMap<>();
        registerData.put("username", username);
        registerData.put("password", SCryptUtil.hashPassword(password)); // 加密后的密码
        registerData.put("email", email);
        registerData.put("code", code);


        String jsonString = objectMapper.writeValueAsString(registerData);
        redisTemplate.opsForValue().set(keyReg + regID, jsonString, timeout, TimeUnit.MINUTES);

        // 发送验证码 - 加入队列
        MailCodeMessage mailCodeMessage = new MailCodeMessage(email, code, MailCodePurpose.UserRegister);
        String message = objectMapper.writeValueAsString(mailCodeMessage);
        rabbitTemplate.convertAndSend(MQExchangeType.DIRECT_EXCHANGE.getValue(), MQRoutingKey.EMAIL_ROUTING_KEY.getKey(), message);

        // 返回 regID
        return new Pair<>(AuthServiceEnum.Success, regID);
    }

    /**
     * 用于验证 注册申请
     *
     * @param regID 注册会话标识符
     * @param code  验证码
     * @return 用户授权服务 状态(枚举)
     */
    public AuthServiceEnum verificationReg(String regID, String code) {

        // 提取用户信息
        String userData = (String) redisTemplate.opsForValue().get(keyReg + regID);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map;
        try {
            map = objectMapper.readValue(userData, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            // 处理解析异常
            return AuthServiceEnum.JsonParseError;
        }
        if (map == null) return AuthServiceEnum.RegIdNotFound;

        // 比对验证码
        String mapCode = map.get("code");
        if (code == null || !code.equals(mapCode)) {
            return AuthServiceEnum.INVALID_CODE; // 验证码错误
        }

        // 验证成功 - 清理 redis 数据
        redisTemplate.delete(keyReg + regID);
        redisTemplate.delete(keyRegPend + map.get("username"));
        redisTemplate.delete(keyRegPend + map.get("email"));

        // 执行注册逻辑
        String uid;      // 获取uid
        do {
            uid = UuidUtil.getUuid();
        } while (userRepository.existsByUid(uid));

        User user = new User();
        user.setUid(uid);
        user.setUsername(map.get("username"));
        user.setPassword(map.get("password"));
        user.setEmail(map.get("email"));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return AuthServiceEnum.Success;
    }
}
