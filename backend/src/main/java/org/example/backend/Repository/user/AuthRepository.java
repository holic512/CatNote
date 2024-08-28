/**
 * CreateTime: 2024-08-27
 * Description: 用户存储库接口
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.Repository.user;

import org.example.backend.Dto.user.AuthDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.backend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthRepository extends JpaRepository<User, Long> {

    @Query("select new org.example.backend.Dto.user.AuthDto(u.uid,u.password) from User u where u.username = :username")
    AuthDto findAuthDtoByUsername(@Param("username") String username);


}