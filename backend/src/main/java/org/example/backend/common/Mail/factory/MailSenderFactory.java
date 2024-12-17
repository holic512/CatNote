/**
 * CreateTime: 2024-08-29
 * Description: 邮箱配置
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.Mail.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.common.ImageStorage.service.impl.Local.LocalImageStorageService;
import org.example.backend.common.Mail.enums.MailSecurityProtocol;
import org.example.backend.common.Mail.pojo.EmailConfiguration;
import org.example.backend.common.Mail.pojo.MailClient;
import org.example.backend.common.Mail.repository.CMailConfigRep;
import org.example.backend.common.entity.ServerConfigData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
public class MailSenderFactory {

    private static final Logger log = LoggerFactory.getLogger(MailSenderFactory.class);
    // 服务配置 和 配置数据 Bean注入
    private final CMailConfigRep mailServerConfigRep;
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    MailSenderFactory(CMailConfigRep mailServerConfigRep) {
        this.mailServerConfigRep = mailServerConfigRep;
    }


    /**
     * 用于启动前 初始化 邮箱对象
     *
     * @return
     */
    @Bean
    public MailClient CreateMailClient() throws JsonProcessingException {
        // 创建数据库对象 - MailClient
        MailClient mailClient = new MailClient();

        // 查询数据库 邮箱是否开启
        int State = mailServerConfigRep.findMailState();

        // state = 1 则关闭 反之 开启
        if (State == 1) {
            logger.info("邮箱服务-关闭");
            mailClient.OffMailClient();
        } else {

            logger.info("邮箱服务-开启");
            // 从数据库中获取邮箱配置
            ServerConfigData configData = mailServerConfigRep.findMailServerConfig();
            String configJson = configData.getConfigJson();
            EmailConfiguration emailConfiguration = new ObjectMapper().readValue(configJson, EmailConfiguration.class);

            // 创建 mailSender 对象
            JavaMailSenderImpl mailSender = getJavaMailSender(emailConfiguration);

            // 插入到 mailClient
            mailClient.OpenMailClient(mailSender);
        }

        // 返回对象
        return mailClient;
    }

    /**
     *
     * @param emailConfiguration 邮箱对象的 配置
     *
     * @return JavaMailSenderImpl 邮箱对象实例
     */
    private static JavaMailSenderImpl getJavaMailSender(EmailConfiguration emailConfiguration) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // 设置邮件服务器主机名
        mailSender.setHost(emailConfiguration.getSmtpServer());
        // 设置邮件服务器端口
        mailSender.setPort(emailConfiguration.getPort());
        // 设置邮箱用户名
        mailSender.setUsername(emailConfiguration.getEmail());
        // 设置邮箱授权码
        mailSender.setPassword(emailConfiguration.getAuthorizationCode());
        // 设置编码格式
        mailSender.setDefaultEncoding("UTF-8");
        // 设置邮件发送的属性
        Properties props = mailSender.getJavaMailProperties();
        // 使用的邮件传输协议
        props.put("mail.transport.protocol", "smtp");
        // 是否需要认证
        props.put("mail.smtp.auth", emailConfiguration.getEncryption());

        // 根据安全协议类型，设置相应的属性
        MailSecurityProtocol securityProtocol = emailConfiguration.getEncryption();
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
