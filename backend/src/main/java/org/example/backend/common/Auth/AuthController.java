/**
 * File Name: AuthController.java
 * Description: 针对于 路由守卫进行鉴权 所提供的 控制器
 * Author: holic512
 * Created Date: 2024-09-11
 * Version: 1.0
 * Usage:
 * 切换受保护的路由时,激活路由守卫,调用此api,进行鉴权,判断权限
 */
package org.example.backend.common.Auth;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("validate")
    public ResponseEntity<Object> validate() {
        if (StpKit.USER.isLogin()) {
            return ResponseEntity.ok(new ApiResponse.Builder<>()
                    .status(200)
                    .message("鉴权成功")
                    .data("USER")
                    .build());
        }

        if (StpKit.ADMIN.isLogin()) {
            return ResponseEntity.ok(new ApiResponse.Builder<>()
                    .status(200)
                    .message("鉴权成功")
                    .data("ADMIN")
                    .build());
        }


        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("鉴权失败")
                .build());
    }

}
