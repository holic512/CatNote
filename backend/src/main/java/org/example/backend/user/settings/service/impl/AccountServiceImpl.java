/**
 * 文件名称: AccountServiceImpl.java
 * 描述: 用户账户服务的实现类，负责从不同的数据源获取用户账户信息并合并处理。
 * 作者: holic512
 * 创建日期: 2024-10-12
 * 版本: 1.0
 * 用法: 该类实现了 AccountService 接口，通过从用户表、用户资料表以及头像服务获取用户的详细信息并返回。
 */
package org.example.backend.user.settings.service.impl;

import org.example.backend.common.UserAvatar.UserAvatarService;
import org.example.backend.user.settings.dto.AccountInfo;
import org.example.backend.user.settings.repository.USUserProfileRep;
import org.example.backend.user.settings.repository.USUserRep;
import org.example.backend.user.settings.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final USUserRep USUserRep;
    private final USUserProfileRep profileRepository;
    private final UserAvatarService userAvatarService;

    @Autowired
    public AccountServiceImpl(USUserRep USUserRep, USUserProfileRep profileRepository, UserAvatarService userAvatarService) {
        this.USUserRep = USUserRep;
        this.profileRepository = profileRepository;
        this.userAvatarService = userAvatarService;
    }


    @Override
    public AccountInfo fetchAccountInfo(String uid) throws Exception {

        // 查询 users 表中的 数据
        AccountInfo users_info = USUserRep.findAccountInfoDtoByUid(uid);
        // 查询 user_profiles 表中的 数据
        AccountInfo user_profile_info = profileRepository.findAccountInfoDtoByUid(uid);
        // 查询 用户头像的 url
        String avatar = userAvatarService.getUserAvatarUrl(users_info.getId());
        // 合并数据

        users_info.setUid(uid);
        users_info.setAvatar(avatar);
        users_info.setUserProfile(user_profile_info);


        return users_info;
    }
}
