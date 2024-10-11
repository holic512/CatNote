/**
 * File Name: MQEmailCode.java
 * Description: 发送到 消息队列 用于序列化的 邮箱验证码消息类
 * Author: holic512
 * Created Date: 2024-09-10
 * Version: 1.0
 * Usage:
 * 将发送的 邮箱验证码信息 添加到此类 并上传到rabbitMQ消息队列中,以便于被消费者读取使用
 */
package org.example.backend.common.Mail.dto;

import org.example.backend.common.Mail.enums.MailCodePurpose;

public class MailCodeMessage {
    private String email;
    private String code;
    private MailCodePurpose purpose;

    // 无参构造函数
    public MailCodeMessage() {
    }

    public MailCodeMessage(String email, String code, MailCodePurpose purpose) {
        this.email = email;
        this.code = code;
        this.purpose = purpose;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MailCodePurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(MailCodePurpose purpose) {
        this.purpose = purpose;
    }
}
