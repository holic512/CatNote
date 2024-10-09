/**
 * File Name: AdminOUserMmRepository.java
 * Description: 用于 管理员 - 在线用户管理 - 持久层接口
 * Author: holic512
 * Created Date: 2024-10-09
 * Version: 1.0
 * Usage:
 * 用于 管理员 - 在线用户管理 - 持久层接口
 */
package org.example.backend.admin.userMm.repository;

import org.example.backend.admin.userMm.dto.OUserInfoDto;
import org.example.backend.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminOUserMmRepository extends JpaRepository<User, Long> {


    // 根据 uid 列表查询用户的用户名和邮箱
    @Query("SELECT new org.example.backend.admin.userMm.dto.OUserInfoDto(u.id,u.uid,u.username, u.email) " +
            "FROM User u WHERE u.uid IN :uids")
    List<OUserInfoDto> findUsernamesAndEmailsByUidList(@Param("uids") List<String> uids);
}
