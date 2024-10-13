/**
 * File Name: AdminUserMmServiceImpl.java
 * Description: 管理员 用户管理 服务类接口 的 实现类
 * Author: holic512
 * Created Date: 2024-09-12
 * Version: 1.0
 * Usage:
 * 管理员 用户管理 服务类接口 的 实现类
 */
package org.example.backend.admin.userMm.service.impl;

import org.example.backend.admin.userMm.enums.AdminUserMmEnum;
import org.example.backend.admin.userMm.service.AdminUserMmService;
import org.example.backend.admin.userMm.request.AddUserRequest;
import org.example.backend.common.UserAvatar.UserAvatarService;
import org.example.backend.common.dto.user.UserDetailDto;
import org.example.backend.common.entity.User;
import org.example.backend.common.repository.UserRepository;
import org.example.backend.common.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserMmServiceImpl implements AdminUserMmService {

    private final UserRepository userRepository;

    // 头像服务
    private final UserAvatarService userAvatarService;

    @Autowired
    public AdminUserMmServiceImpl(UserRepository userRepository, UserAvatarService userAvatarService) {
        this.userRepository = userRepository;
        this.userAvatarService = userAvatarService;
    }

    @Override
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    public List<UserDetailDto> fetchInitialUser(int count) {
        // 判断 查询范围
        Pageable pageable = PageRequest.of(0, count);

        // 查询数据库中用户信息
        List<UserDetailDto> users = userRepository.findUserInRange(pageable);

        // 查询用户头像
        for (UserDetailDto user : users) {
            try {
                String avatarUrl = userAvatarService.getUserAvatarUrl(user.getId());
                user.setAvatar(avatarUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    @Override
    public List<UserDetailDto> findUserInRange(int start, int end) {
        // 判断 查询范围
        Pageable pageable = PageRequest.of(start, end);

        // 查询数据库中用户信息
        List<UserDetailDto> users = userRepository.findUserInRange(pageable);

        // 查询用户头像
        for (UserDetailDto user : users) {
            try {
                String avatarUrl = userAvatarService.getUserAvatarUrl(user.getId());
                user.setAvatar(avatarUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public AdminUserMmEnum addUser(AddUserRequest addUserRequest) {

        // 查询数据库中是否存在此用户名 或 邮箱
        boolean existUsername = userRepository.existsByUsername(addUserRequest.getUsername());
        if (existUsername) {
            return AdminUserMmEnum.UserAlreadyExists;
        }
        boolean existEmail = userRepository.existsByEmail(addUserRequest.getEmail());
        if (existEmail) {
            return AdminUserMmEnum.EmailAlreadyExists;
        }

        // 生成不重复的UID
        String uid;      // 获取uid
        do {
            uid = UuidUtil.getUid();
        } while (userRepository.existsByUid(uid));

        // 整合到User类中
        User user = new User.Builder()
                .uid(uid)
                .username(addUserRequest.getUsername())
                .password(addUserRequest.getPassword())
                .email(addUserRequest.getEmail())
                .status(addUserRequest.getStatus().getValue())
                .build();

        // success
        userRepository.save(user);

        return AdminUserMmEnum.Success;
    }

    @Override
    public boolean deleteUsers(List<Long> userIds) {
        try {
            userRepository.deleteUsersByIds(userIds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}






