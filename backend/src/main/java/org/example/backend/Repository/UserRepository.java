/**
 * CreateTime: 2024-08-27
 * Description: 用户存储库接口
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.Repository;

import org.example.backend.Dto.user.AuthDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.backend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    // 查询用于登录中的所获取的用户密码和UID
    @Query("select new org.example.backend.Dto.user.AuthDto(u.uid,u.password) from User u where u.username = :username")
    AuthDto findAuthDtoByUsername(@Param("username") String username);

    // 查询用于登录中的所获取的用户密码和UID
    @Query("select new org.example.backend.Dto.user.AuthDto(u.uid) from User u where u.email = :email")
    AuthDto findAuthDtoByEmail(@Param("email") String email);

    // 判断邮箱是否存在
    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    boolean existsByEmail(@Param("email") String email);

}