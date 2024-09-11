/**
 * File Name: AuthController.java
 * Description: 针对于 路由守卫进行鉴权 所提供的 控制器
 * Author: holic512
 * Created Date: 2024-09-11
 * Version: 1.0
 * Usage:
 * 切换受保护的路由时,激活路由守卫,调用此api,进行鉴权,判断权限
 */
package org.example.backend.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("validate")
    public String validate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().toString();
        }
        return null;
    }
}
