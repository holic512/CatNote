/**
 * CreateTime: 2024-08-27
 * Description: 用户存储库接口
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.backend.common.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    // 查询用于登录中的所获取的用户 密码和UID
    @Query("select new org.example.backend.common.entity.User(u.uid,u.password,u.status) from User u where u.username = :username")
    User findAuthDtoByUsername(@Param("username") String username);

    // 查询用于登录中的所获取的用户 密码和UID
    @Query("select new org.example.backend.common.entity.User(u.uid,u.status) from User u where u.email = :email")
    User findAuthDtoByEmail(@Param("email") String email);

    // 判断 email 是否存在
    boolean existsByEmail(String email);

    // 判断 username 是否存在
    boolean existsByUsername(String username);

    // 判断 uid 是否存在
    boolean existsByUid(String uid);

    // 获取用户数目
    long count();

    // 获取指定范围内的用户数据 - 管理员后台-用户管理
    @Query("SELECT new org.example.backend.common.entity.User(u.id,u.uid, u.username, u.email, u.status) " +
            "FROM User u WHERE u.id BETWEEN :start AND :end")
    List<User> findUserInRange(Long start, Long end);

}