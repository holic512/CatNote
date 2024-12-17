/**
 * CreateTime: 2024-08-29
 * Description: 邮箱加密协议枚举
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.Mail.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MailSecurityProtocol {
    NONE,       // 不使用加密
    STARTTLS,   // 使用 STARTTLS 升级到加密
    SSL_TLS;   // 直接使用 SSL/TLS 加密


    @JsonCreator
    public static MailSecurityProtocol fromValue(String value) {
        switch (value) {
            case "NONE":
                return NONE;
            case "STARTTLS":
                return STARTTLS;
            case "SSL_TLS":
                return SSL_TLS;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}

