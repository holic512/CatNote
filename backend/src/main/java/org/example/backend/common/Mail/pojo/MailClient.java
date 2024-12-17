/**
 * File Name: MailSender.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-12
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.common.Mail.pojo;

import lombok.Data;
import org.springframework.mail.javamail.JavaMailSender;


@Data
public class MailClient {

    // 判断邮箱功能是否开启 true-开启 false-关闭
    boolean state = false;

    // JavaMailSender 实例
    JavaMailSender MailSender = null;

    /**
     * 关闭 邮箱服务
     */
    public void OffMailClient() {
        this.state = false;
        this.MailSender = null;
    }

    /**
     * 开启 邮箱服务
     *
     * @param MailSender 邮箱对象
     */
    public void OpenMailClient(JavaMailSender MailSender) {
        this.state = true;
        this.MailSender = MailSender;
    }

    public boolean getState() {
        return this.state;
    }

}
