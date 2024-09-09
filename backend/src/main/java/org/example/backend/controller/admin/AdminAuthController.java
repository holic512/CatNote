/**
 * File Name: AuthController.java
 * Description: 管理员身份验证 控制器
 * Author: holic512
 * Created Date: 2024-09-04
 * Version: 1.0
 * Usage:
 * 前端调用 restful接口 进行身份验证
 */
package org.example.backend.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.config.ApiResponse;
import org.example.backend.enums.admin.AuthServiceEnum;
import org.example.backend.service.admin.AdminAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {

    AdminAuthService adminAuthService;

    @Autowired
    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> requestBody) throws JsonProcessingException {
        String username = requestBody.get("username");
        String password = requestBody.get("password");

        if (username == null || password == null) {
            return ResponseEntity.ok(new ApiResponse<>(404, "用户名或密码为空"));
        }

        // 调用服务类
        Pair<AuthServiceEnum, String> result = adminAuthService.login(username, password);

        switch (result.a) {
            case Success -> {
                return ResponseEntity.ok(new ApiResponse<>(200, "发送成功", result.b));
            }
            case UserNotExists -> {
                return ResponseEntity.ok(new ApiResponse<>(404, "用户不存在"));
            }
            case INCORRECT -> {
                return ResponseEntity.ok(new ApiResponse<>(401, "密码错误"));
            }
            case EmailSendFailure -> {
                return ResponseEntity.ok(new ApiResponse<>(405, "邮箱发送失败"));
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse<>(500, "无法连接服务器"));
            }

        }
    }

    @PostMapping("verLogin")
    public ResponseEntity<Object> verLogin(@RequestBody Map<String, String> requestBody) throws JsonProcessingException {
        String logID = requestBody.get("logID");
        String code = requestBody.get("code");


        if (code == null || logID == null) {
            return ResponseEntity.ok(new ApiResponse<>(404, "登录验证码或验证请求为空"));
        }

        // service
        Pair<AuthServiceEnum, String> result = adminAuthService.verLogin(logID, code);

        switch (result.a) {
            case Success -> {
                return ResponseEntity.ok(new ApiResponse<>(200, "管理员登陆成功", result.b));
            }
            case JsonParseError -> {
                return ResponseEntity.ok(new ApiResponse<>(400, "json解析异常"));
            }
            case RegIdNotFound -> {
                return ResponseEntity.ok(new ApiResponse<>(404, "未找到注册请求"));
            }
            case INVALID_CODE -> {
                return ResponseEntity.ok(new ApiResponse<>(401, "验证码无效"));
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse<>(500, "无法连接服务器"));
            }
        }
    }


}
