/**
 * File Name: ServerConfigDataRepository.java
 * Description: 服务器配置数据的 JPA 仓库接口
 * Author: holic512
 * Created Date: 2024-10-10
 * Version: 1.0
 * Usage: 提供对服务器配置数据的 CRUD 操作，包括通过配置项名称查找配置数据。
 */
package org.example.backend.common.ImageStorage.repository;

import org.example.backend.common.entity.ServerConfigData;
import org.springframework.data.repository.CrudRepository;

/**
 * ServerConfigDataRepository 接口用于访问服务器配置数据。
 * 继承自 CrudRepository，提供基本的 CRUD 操作。
 */
public interface ServerConfigDataRepository extends CrudRepository<ServerConfigData, Long> {

    /**
     * 根据配置项名称查找配置数据。
     *
     * @param configName 配置项名称
     * @return 返回对应的 ServerConfigData 对象，如果未找到则返回 null
     */
    ServerConfigData findConfigJsonByConfigName(String configName);
}
