/**
 * CreateTime: 2024-08-27
 * Description: 用户服务类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.service;

import org.example.backend.Dto.user.AuthDto;
import org.example.backend.Repository.user.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AuthRepository authRepository;

    // 通过构造函数注入
    @Autowired
    public UserService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public AuthDto findAuthDtoByUsername(String username) {
        return authRepository.findAuthDtoByUsername(username);
    }


}
