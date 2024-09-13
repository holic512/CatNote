/**
 * File Name: AdminUserMmServiceImpl.java
 * Description: 管理员 用户管理 服务类接口 的 实现类
 * Author: holic512
 * Created Date: 2024-09-12
 * Version: 1.0
 * Usage:
 * 管理员 用户管理 服务类接口 的 实现类
 */
package org.example.backend.admin.service.userMm;

import org.example.backend.common.entity.User;
import org.example.backend.common.repository.AdminRepository;
import org.example.backend.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserMmServiceImpl implements AdminUserMmService {

    private final UserRepository userRepository;

    @Autowired
    public AdminUserMmServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    public List<User> fetchInitialUser(int count) {
        return userRepository.findUserInRange(0L, (long) count);
    }

    @Override
    public List<User> findUserInRange(long start, long end) {
        return userRepository.findUserInRange(start, end);
    }


}






