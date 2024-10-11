/**
 * File Name: AdminUserMmController.java
 * Description: 管理员 用户管理 控制器
 * Author: holic512
 * Created Date: 2024-09-11
 * Version: 1.0
 * Usage:
 * 用来定义 用户管理 的api,给前端操作
 */
package org.example.backend.admin.userMm.Controller;

import jakarta.validation.Valid;
import org.example.backend.admin.userMm.enums.AdminUserMmEnum;
import org.example.backend.admin.userMm.request.AddUserRequest;
import org.example.backend.admin.userMm.service.AdminUserMmService;
import org.example.backend.common.dto.user.UserDetailDto;
import org.example.backend.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("成功获取用户数目")
                .data(count)
                .build());
    }


    /**
     * 获取 初始页用户数据
     *
     * @param count 初始页 显示的行数
     * @return 存储指定范围用户数据的list
     */
    @GetMapping("fetchInitialUser")
    public ResponseEntity<Object> fetchInitialUser(@RequestParam int count) {
        List<UserDetailDto> userData = adminUserMmService.fetchInitialUser(count);
        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("成功获取第一页的数据")
                .data(userData)
                .build());
    }

    /**
     * 获取 指定范围的 用户数据
     *
     * @param pageNum  页数
     * @param pageSize 行数大小
     * @return 存储指定范围用户数据的list
     */
    @GetMapping("/fetchPageData")
    public ResponseEntity<Object> fetchPageData(@RequestParam int pageNum, @RequestParam int pageSize) {

        // 调用服务层
        List<UserDetailDto> users = adminUserMmService.findUserInRange(pageNum - 1, pageSize);
        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(200)
                .message("成功获取分页数据")
                .data(users)
                .build());
    }

    /**
     * 添加 用户
     *
     * @param addUserRequest 用于 添加用户 的 请求体
     * @return 操作状态
     */
    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@Valid @RequestBody AddUserRequest addUserRequest) {
        AdminUserMmEnum result = adminUserMmService.addUser(addUserRequest);
        switch (result) {
            case Success -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(200)
                        .message("添加成功")
                        .build());
            }
            case EmailAlreadyExists -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(40901)
                        .message("邮箱已存在")
                        .build());
            }
            case UserAlreadyExists -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(40901)
                        .message("用户名已存在")
                        .build());
            }
            default -> {
                return ResponseEntity.ok(new ApiResponse.Builder<>()
                        .status(500)
                        .message("无法连接服务器")
                        .build());
            }
        }
    }


    /**
     * 删除 用户
     *
     * @param userIds 存储了用户 id 的列表
     * @return 状态响应体
     */
    @PostMapping("/batchDeleteUser")
    public ResponseEntity<Object> batchDeleteUser(@RequestBody List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse.Builder<>().status(404).message("用户列表为空").build());
        }

        boolean result = adminUserMmService.deleteUsers(userIds);

        if (result) {
            return ResponseEntity.ok(new ApiResponse.Builder<>().status(200).message("批量删除成功").build());
        } else
            return ResponseEntity.ok(new ApiResponse.Builder<>().status(500).build());

    }


}
