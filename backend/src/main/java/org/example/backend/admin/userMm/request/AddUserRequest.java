/**
 * File Name: addUserRequest.java
 * Description: 用于用户管理-添加用户 的请求体类
 * Author: holic512
 * Created Date: 2024-09-18
 * Version: 1.0
 * Usage:
 * 作为添加用户接口参数类型,并使用JSR 380规范约束
 */
package org.example.backend.admin.userMm.request;

import lombok.Data;
import org.example.backend.common.enums.UserStatusEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class AddUserRequest {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 6, max = 12, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Status cannot be null")
    private UserStatusEnum status;
}
