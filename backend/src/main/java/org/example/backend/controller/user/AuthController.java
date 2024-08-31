/**
 * CreateTime: 2024-08-27
 * Description: 用于用户登录控制器
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.controller.user;

import jakarta.mail.MessagingException;
import org.example.backend.config.ApiResponse;
import org.example.backend.service.user.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@RestController
@RequestMapping("/user")
public class AuthController {
    private final AuthService authService;

    @Autowired
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 邮箱格式验证规则
    final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    /**
     * 使用 账号密码 的 登录
     *
     * @param requestBody 请求体
     * @return http状态码
     */
    @PostMapping("/PLogin")
    public ResponseEntity<Object> PLogin(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        String result = authService.PLogin(username, password);
        // 账号未找到
        if (Objects.equals(result, "-1")) {
            return ResponseEntity.ok(new ApiResponse<>(404, "账号不存在"));
        }
        // 密码错误
        else if (Objects.equals(result, "-2")) {
            return ResponseEntity.ok(new ApiResponse<>(401, "账号密码错误"));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "登录成功", result));
    }

    /**
     * 发送用于邮箱登录的验证码
     *
     * @param requestBody 请求体
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
        boolean result = authService.sendLoginCode(email);
        if (!result) {
            return ResponseEntity.ok(new ApiResponse<>(404, "邮箱不存在"));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "验证码成功发送"));
    }


    /**
     * 验证 用于 邮箱登录 的 验证码
     *
     * @param requestBody 请求体
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
        String result = authService.verifyLoginCode(email, code);

        if (result == null) {
            return ResponseEntity.ok(new ApiResponse<>(401, "邮箱登录验证失败"));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "登录成功", result));
    }


}
