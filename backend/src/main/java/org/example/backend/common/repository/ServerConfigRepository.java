/**
 * File Name: ServerConfigRepository.java
 * Description: ServerConfig 实体的仓库接口，用于对服务器配置数据进行 CRUD 操作。
 * Author: holic512
 * Created Date: 2024-10-10
 * Version: 1.0
 * Usage: 通过此接口可以对服务器配置表进行增删改查操作。
 */
package org.example.backend.common.repository;

import org.example.backend.common.entity.ServerConfig;
import org.springframework.data.repository.CrudRepository;

public interface ServerConfigRepository extends CrudRepository<ServerConfig, Long> {
    // 可以在这里添加自定义查询方法，例如
    // Optional<ServerConfig> findByImageStorageMode(Integer imageStorageMode);
}
