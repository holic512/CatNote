/**
 * CreateTime: 2024-08-27
 * Description: 邮箱配置存储库接口
 * Version: 1.0
 * Author: holic512
 */

package org.example.backend.Repository;

import org.example.backend.entity.EmailConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailConfiguration, Long> {

}
