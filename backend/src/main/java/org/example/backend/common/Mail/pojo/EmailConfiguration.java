/**
 * 文件名: EmailConfiguration.java
 * 描述: 电子邮件配置类，包含 SMTP 服务器信息及相关配置
 * 作者: holic512
 * 创建日期: 2024-12-12
 * 版本: 1.0
 * 用法: 用于保存邮件发送配置的属性，支持 JSON 反序列化
 */

package org.example.backend.common.Mail.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.backend.common.Mail.enums.MailSecurityProtocol;

/**
 * 邮件配置类
 */
@Data
public class EmailConfiguration {

    /**
     * SMTP 服务器地址
     */
    @JsonProperty("smtpServer")
    private String smtpServer;

    /**
     * 邮件服务器端口号
     */
    @JsonProperty("port")
    private int port;

    /**
     * 发送邮件的邮箱地址
     */
    @JsonProperty("email")
    private String email;

    /**
     * 邮箱授权码，用于验证登录
     */
    @JsonProperty("authorizationCode")
    private String authorizationCode;

    /**
     * 是否需要认证
     */
    @JsonProperty("authenticationRequired")
    private boolean authenticationRequired;

    /**
     * 邮件加密方式（如 SSL/TLS）
     */
    @JsonProperty("encryption")
    private MailSecurityProtocol encryption;

    @Override
    public String toString() {
        return "EmailConfiguration [smtpServer=" + smtpServer + ", port=" + port + ", email=" + email
                + ", authorizationCode=" + authorizationCode + ", authenticationRequired=" + authenticationRequired + ", encryption="
                + encryption + "]";
    }
}
