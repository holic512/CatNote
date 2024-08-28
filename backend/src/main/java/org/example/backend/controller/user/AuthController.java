/**
 * CreateTime: 2024-08-27
 * Description: 用于用户登录控制器
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.controller.user;

import org.example.backend.Dto.user.AuthDto;
import org.example.backend.Request.user.AuthRequest;
import org.example.backend.service.UserService;
import org.example.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {
    private final UserService userService;

    @Autowired
    AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest loginRequest) {
        final String username = loginRequest.getUsername();
        final String password = loginRequest.getPassword();

        final AuthDto authDto = userService.findAuthDtoByUsername(username);

        if (authDto == null) {
            return ResponseEntity.status(404).build();
        }

        if (!password.equals(authDto.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok(JwtUtil.generateToken(authDto.getUid(), "user"));

    }
}
