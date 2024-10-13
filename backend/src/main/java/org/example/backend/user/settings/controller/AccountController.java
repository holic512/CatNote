/**
 * 文件名称: accountController.java
 * 描述: 该控制器处理用户账户设置操作，包括获取用户的个人资料信息。
 * 作者: holic512
 * 创建日期: 2024-10-12
 * 版本: 1.0
 * 用法: 该类提供了一个API端点，用于根据用户的登录信息获取用户账户资料。
 */

package org.example.backend.user.settings.controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.settings.dto.AccountInfo;
import org.example.backend.user.settings.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/settings/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("userAllProfile")
    public ResponseEntity<Object> userAllProfile() throws Exception {
        // 获取uid
        String uid = (String) StpKit.USER.getLoginId();

        AccountInfo accountInfo = accountService.fetchAccountInfo(uid);

        return ResponseEntity.ok(new ApiResponse.Builder<AccountInfo>()
                .status(200)
                .message("查询成功")
                .data(accountInfo)
                .build()
        );

    }

}
