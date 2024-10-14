/**
 * 文件名称: AccountService.java
 * 描述: 用户账户服务接口，用于定义获取用户账户相关信息的业务逻辑。
 * 作者: holic512
 * 创建日期: 2024-10-12
 * 版本: 1.0
 * 用法: 该接口提供用于获取用户页面设置中所需的用户信息的方法。
 */
package org.example.backend.user.settings.service;

import org.example.backend.common.entity.UserProfile;
import org.example.backend.user.settings.dto.AccountInfo;

public interface AccountService {

    /**
     * 获取用户页面设置中的用户信息
     *
     * @param uid 用户的唯一标识符
     * @return 用户的账户信息
     * @throws Exception 抛出异常
     */
    AccountInfo fetchAccountInfo(String uid) throws Exception;

    /**
     * 修改用户昵称
     *
     * @param uid
     * @param userProfile
     * @return
     * @throws Exception
     */
    AccountInfo EditAccountNickName(String uid, UserProfile userProfile) throws Exception;

    AccountInfo EditAccountGender(String uid, UserProfile userProfile) throws Exception;

    AccountInfo EditAccountAge(String uid, UserProfile userProfile) throws Exception;

    AccountInfo EditAccountBio(String uid, UserProfile userProfile) throws Exception;

    AccountInfo EditAccountContact(String uid, UserProfile userProfile) throws Exception;
}
