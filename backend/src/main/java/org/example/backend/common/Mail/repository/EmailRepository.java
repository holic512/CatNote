/**
 * CreateTime: 2024-08-27
 * Description: 邮箱配置 持久层接口
 * Version: 1.0
 * Author: holic512
 */

package org.example.backend.common.Mail.repository;

import org.example.backend.common.entity.EmailConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailConfiguration, Long> {

}
