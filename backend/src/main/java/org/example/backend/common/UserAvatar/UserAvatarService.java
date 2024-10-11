/**
 * File Name: UserAvatarService.java
 * Description: 用户头像服务接口，用于管理用户头像的上传和获取。
 * Author: holic512
 * Created Date: 2024-10-11
 * Version: 1.0
 * Usage: 通过实现此接口，可以提供用户头像的修改和获取功能。
 */
package org.example.backend.common.UserAvatar;

import org.springframework.web.multipart.MultipartFile;

public interface UserAvatarService {

    /**
     * 修改用户头像
     *
     * @param userId 用户ID
     * @param avatarFile 头像文件对象（可以是MultipartFile或文件路径）
     * @return 返回新的头像URL
     * @throws Exception 如果上传失败，抛出异常
     */
    String updateUserAvatar(String userId, MultipartFile avatarFile) throws Exception;

    /**
     * 获取用户头像URL
     *
     * @param userId 用户ID
     * @return 头像的URL地址
     * @throws Exception 如果头像不存在或获取失败，抛出异常
     */
    String getUserAvatarUrl(Long userId) throws Exception;
}
