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

import org.example.backend.admin.service.userMm.AdminUserMmService;
import org.example.backend.common.dto.ApiResponse;
import org.example.backend.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/userMm")
public class AdminUserMmController {

    AdminUserMmService adminUserMmService;

    @Autowired
    public AdminUserMmController(AdminUserMmService adminUserMmService) {
        this.adminUserMmService = adminUserMmService;
    }

    /**
     * 获取用户总数
     *
     * @return 带有用户数量的 响应体
     */
    @GetMapping("getUserCount")
    public ResponseEntity<Object> getUserCount() {
        long count = adminUserMmService.getUserCount();

        return ResponseEntity.ok(new ApiResponse<>()
                .status(200)
                .message("成功获取用户数目")
                .data(count));
    }


    /**
     * 获取 初始页用户数据
     *
     * @param count 初始页 显示的行数
     * @return
     */
    @GetMapping("fetchInitialUser")
    public ResponseEntity<Object> fetchInitialUser(@RequestParam int count) {
        List<User> userData = adminUserMmService.fetchInitialUser(count);
        return ResponseEntity.ok(new ApiResponse<>()
                .status(200)
                .message("成功获取第一页的数据")
                .data(userData));
    }

    @GetMapping("/fetchPageData")
    public ResponseEntity<Object> fetchPageData(@RequestParam int pageNum, @RequestParam int pageSize) {
        // 计算偏移量和结束位置
        long start = (long) (pageNum - 1) * pageSize; // 偏移量 (start)
        long end = start + pageSize;       // 结束索引 (end)

        // 当 start 不等 0 证明不是第一页 起始页面 向前偏移一位
        if (start != 0) start += 1;

        // 调用服务层
        List<User> users = adminUserMmService.findUserInRange(start, end);

        // 构建响应
        return ResponseEntity.ok(new ApiResponse<>()
                .status(200)
                .message("成功获取分页数据")
                .data(users));
    }

}
