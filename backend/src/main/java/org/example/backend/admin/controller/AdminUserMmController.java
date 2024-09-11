/**
 * File Name: AdminUserMmController.java
 * Description: 管理员 用户管理 控制器
 * Author: holic512
 * Created Date: 2024-09-11
 * Version: 1.0
 * Usage:
 * 用来定义 用户管理 的api,给前端操作
 */
package org.example.backend.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/userMm")
public class AdminUserMmController {

    // 测试接口 只有带有 admin 权限才可以调用
    @GetMapping("test")
    public String test() {
        return "test";
    }


}
