/**
 * CreateTime: 2024-08-27
 * Description: 用户登录控制器
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.config.ApiResponse;
import org.example.backend.enums.user.AuthServiceEnum;
import org.example.backend.service.user.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@RestController
@RequestMapping("/user")
public class UserAuthController {
    private final UserAuthService userAuthService;

    @Autowired
    UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    // 邮箱格式验证规则
    final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    /**
     * 使用 账号密码 的 登录
     *
     * @param requestBody 请求体
     *                    username 账号
     *                    password 密码
     * @return ResponseEntity 响应体
     */
    @PostMapping("/PLogin")
    public ResponseEntity<Object> PLogin(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        if (username.isEmpty() || password.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(400, "参数格式不正确"));
        }

        // 调用服务层
        Pair<AuthServiceEnum, String> result = userAuthService.PLogin(username, password);

        switch (result.a) {
            case Success -> {
                return ResponseEntity.ok(new ApiResponse<>(200, "登录成功", result));
            }
            case UserNotFound -> {
                return ResponseEntity.ok(new ApiResponse<>(404, "账号不存在"));
            }
            case INCORRECT -> {
                return ResponseEntity.ok(new ApiResponse<>(401, "账号密码错误"));
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse<>(500, "无法连接服务器"));
            }
        }
    }

    /**
     * 发送用于邮箱登录的验证码
     *
     * @param requestBody 请求体
     *                    email 邮箱地址
     * @return http状态码
     */
    @PostMapping("/sendLoginCode")
    public ResponseEntity<Object> sendLoginCode(@RequestBody Map<String, String> requestBody) throws MessagingException {

        String email = requestBody.get("email");

        // 检测参数是否规范
        if (email == null || email.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(400, "参数格式不正确"));
        }

        // 使用正则表达式检查邮箱格式
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            return ResponseEntity.ok(new ApiResponse<>(400, "参数格式不正确"));
        }

        // service
        boolean result = userAuthService.sendLoginCode(email);
        if (!result) {
            return ResponseEntity.ok(new ApiResponse<>(404, "邮箱不存在"));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "验证码成功发送"));
    }


    /**
     * 验证 用于 邮箱登录 的 验证码
     *
     * @param requestBody 请求体,
     *                    email 邮箱,
     *                    code 验证码
     * @return 状态码
     */
    @PostMapping("/verifyLoginCode")
    public ResponseEntity<Object> verifyLoginCode(@RequestBody Map<String, String> requestBody) {

        String email = requestBody.get("email");
        String code = requestBody.get("code");

        // 检测参数是否规范
        if (email == null || email.isEmpty() || code == null || code.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(400, "参数格式不正确"));
        }

        // 验证 邮箱登录 验证码 服务层
        String token = userAuthService.verifyLoginCode(email, code);

        if (token == null) {
            return ResponseEntity.ok(new ApiResponse<>(401, "邮箱登录验证失败"));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "登录成功", token));
    }

    @PostMapping("/initiateReg")
    public ResponseEntity<Object> register(@RequestBody Map<String, String> requestBody) throws MessagingException, JsonProcessingException {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        String email = requestBody.get("email");

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || username.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse<>(400, "参数格式不正确"));
        }

        // 服务层
        Pair<AuthServiceEnum, String> regService = userAuthService.initiateReg(username, password, email);

        switch (regService.a) {
            case Success -> {
                return ResponseEntity.ok(new ApiResponse<>(200, "请求注册成功", regService.b));
            }
            case EmailAlreadyExists -> {
                return ResponseEntity.ok(new ApiResponse<>(409, "邮箱已存在"));
            }
            case UserAlreadyExists -> {
                return ResponseEntity.ok(new ApiResponse<>(409, "用户已存在"));
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse<>(500, "无法连接服务器"));
            }
        }
    }

    @PostMapping("/verificationReg")
    public ResponseEntity<Object> verificationReg(@RequestBody Map<String, String> requestBody) throws MessagingException {
        // 验证数据
        String regID = requestBody.get("regID");
        String code = requestBody.get("code");
        if (StringUtils.isBlank(regID) || StringUtils.isBlank(regID) || StringUtils.isBlank(code) || StringUtils.isBlank(code)) {
            return ResponseEntity.ok(new ApiResponse<>(400, "参数格式不正确"));
        }

        // 服务层
        AuthServiceEnum authServiceEnum = userAuthService.verificationReg(regID, code);

        switch (authServiceEnum) {
            case Success -> {
                return ResponseEntity.ok(new ApiResponse<>(200, "注册成功"));
            }
            case JsonParseError -> {
                return ResponseEntity.ok(new ApiResponse<>(409, "json解析失败"));
            }
            case RegIdNotFound -> {
                return ResponseEntity.ok(new ApiResponse<>(409, "未找到注册请求"));
            }
            case INVALID_CODE -> {
                return ResponseEntity.ok(new ApiResponse<>(409, "验证码错误"));
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse<>(500, "无法连接服务器"));
            }
        }

    }


}
