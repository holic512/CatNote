/**
 * 文件名: CMailServerConfigRep.java
 * 描述: 邮件服务器配置相关的数据库操作接口
 * 作者: holic512
 * 创建日期: 2024-12-12
 * 版本: 1.0
 * 用法: 用于查询邮件配置和邮件启用状态
 */
package org.example.backend.common.Mail.repository;

import org.example.backend.common.Mail.pojo.EmailConfiguration;
import org.example.backend.common.entity.ServerConfig;
import org.example.backend.common.entity.ServerConfigData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CMailConfigRep extends JpaRepository<ServerConfig, Long> {

    /**
     * 获取邮件功能的启用状态
     *
     * @return 邮件功能是否开启的状态 (0 表示关闭，1 表示开启)
     */
    @Query("select s.MailMode from ServerConfig s where s.id = 1")
    int findMailState();


    /**
     * 获取邮件服务器的配置信息
     *
     * @return 返回存储的邮件配置信息
     */
    @Query("select sd from ServerConfigData sd where sd.configType = 'MailConfig'")
    ServerConfigData findMailServerConfig();
}
