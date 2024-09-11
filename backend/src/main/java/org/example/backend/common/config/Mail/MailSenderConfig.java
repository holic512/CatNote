/**
 * CreateTime: 2024-08-29
 * Description: 邮箱配置
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.config.Mail;

import org.example.backend.common.entity.EmailConfiguration;
import org.example.backend.common.enums.MailSecurityProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.example.backend.common.repository.EmailRepository;

import java.util.Properties;


@Configuration
public class MailSenderConfig {

    // Bean注入
    private final EmailRepository emailRepository;

    @Autowired
    MailSenderConfig(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    @Bean
    public JavaMailSender javaMailSender() {

        // 获取数据库配置
        EmailConfiguration emailConfiguration = emailRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Email not found"));


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfiguration.getHost());                   // 设置邮件服务器主机名
        mailSender.setPort(emailConfiguration.getPort());                   // 设置邮件服务器端口
        mailSender.setUsername(emailConfiguration.getUsername());           // 设置邮箱用户名
        mailSender.setPassword(emailConfiguration.getPassword());           // 设置邮箱密码
        mailSender.setDefaultEncoding("UTF-8");                             // 设置编码格式

        // 设置邮件发送的属性
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");                       // 使用的邮件传输协议
        props.put("mail.smtp.auth", emailConfiguration.isAuth());           // 是否需要认证

        // 根据安全协议类型，设置相应的属性
        MailSecurityProtocol securityProtocol = emailConfiguration.getMailSecurityProtocol();
        switch (securityProtocol) {
            case SSL_TLS:
                props.put("mail.smtp.ssl.enable", "true");
                props.put("mail.smtp.starttls.enable", "false");
                break;
            case STARTTLS:
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.ssl.enable", "false");
                break;
            case NONE:
                props.put("mail.smtp.ssl.enable", "false");
                props.put("mail.smtp.starttls.enable", "false");
                break;
        }
        // 开启调试模式（可选）
        props.put("mail.debug", "false");

        return mailSender;
    }
}
