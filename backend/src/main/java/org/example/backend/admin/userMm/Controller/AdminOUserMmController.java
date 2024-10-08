/**
 * File Name: AdminOUSerMmContorller.java
 * Description: 管理员 用户管理 - 在线用户管理 控制器
 * Author: holic512
 * Created Date: 2024-09-23
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.admin.userMm.Controller;

import org.example.backend.admin.userMm.service.AdminOUserMmService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/oUserMm")
public class AdminOUserMmController {

    private static AdminOUserMmService adminOUserMmService;


    public AdminOUserMmController(AdminOUserMmService adminOUserMmService) {
        AdminOUserMmController.adminOUserMmService = adminOUserMmService;
    }

    /**
     *
     * @return
     */
    @GetMapping("OUserCount")
    public int fetchOUserCount() {
        return adminOUserMmService.fetchOUserCount();
    }

    
}
