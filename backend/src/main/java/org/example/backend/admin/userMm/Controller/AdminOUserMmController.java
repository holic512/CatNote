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

import org.example.backend.admin.userMm.request.FetchPageData;
import org.example.backend.admin.userMm.service.AdminOUserMmService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/oUserMm")
public class AdminOUserMmController {

    private static AdminOUserMmService adminOUserMmService;


    public AdminOUserMmController(AdminOUserMmService adminOUserMmService) {
        AdminOUserMmController.adminOUserMmService = adminOUserMmService;
    }

    /**
     * 获取在线用户数量 - redis 中存在 session
     *
     * @return 在线数量
     */
    @GetMapping("OUserCount")
    public int fetchOUserCount() {
        return adminOUserMmService.fetchOUserCount();
    }

    /**
     * 获取指定 页数的 用户数据
     */
    @PostMapping("fetchPageData")
    public ResponseEntity<Object> fetchPageData(@RequestBody @Validated FetchPageData fetchPageData) {
        return adminOUserMmService.fetchPageDate(fetchPageData);
    }

    @PostMapping("logout")
    public ResponseEntity<Object> logout(@RequestBody Map<String, Object> request) {
        String uid = request.get("uid").toString();
        return adminOUserMmService.logout(uid);
    }

    @PostMapping("kickout")
    public ResponseEntity<Object> kickout(@RequestBody Map<String, Object> request) {
        String uid = request.get("uid").toString();
        return adminOUserMmService.kickout(uid);
    }

}
