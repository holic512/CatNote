/**
 * File Name: AdminUserMmService.java
 * Description: 管理员 用户管理 服务类接口
 * Author: holic512
 * Created Date: 2024-09-12
 * Version: 1.0
 * Usage:
 * 管理员 用户管理 服务类接口
 */
package org.example.backend.admin.service.userMm;

import org.example.backend.common.entity.User;

import java.util.List;

public interface AdminUserMmService {

    /**
     * 查询 user表中的总数
     *
     * @return user表中行数
     */
    Long getUserCount();


    /**
     * 查询初始页面 (0 - count行) 的 用户数据
     *
     * @param count 初始页面(第一页)有多少行
     * @return 带有用户数据的 列表
     */
    List<User> fetchInitialUser(int count);

    /**
     * 查询 范围start-end的 用户数据
     *
     * @param start 偏移量
     * @param end   结束位置
     * @return 带有用户数据的 列表
     */
    List<User> findUserInRange(long start, long end);
}
