/**
 * CreateTime: 2024-08-29
 * Description: 邮箱工具类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.Mail.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.backend.common.Mail.dto.MailCodeMessage;
import org.example.backend.common.Mail.pojo.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.example.backend.common.Mail.enums.MailCodePurpose;

@Component
public class MailCodeService {

    private final MailClient mailClient;
    private final TemplateEngine templateEngine;

    @Autowired
    public MailCodeService(MailClient mailClient, TemplateEngine templateEngine) {
        this.mailClient = mailClient;
        this.templateEngine = templateEngine;
    }

    /**
     * @param mailCodeMessage 发送邮箱验证码的信息类
     * @return 发送状态
     */
    public boolean sendVerificationCode(MailCodeMessage mailCodeMessage) {
        // 判断邮箱功能 是否开启
        if (!mailClient.getState()) {
            // 如果没开启 则直接返回发送成功
            return true;
        }

        // 开启
        try {
            JavaMailSender mailSender = mailClient.getMailSender();

            String email = mailCodeMessage.getEmail();
            String code = mailCodeMessage.getCode();
            MailCodePurpose purpose = mailCodeMessage.getPurpose();

            // 创建邮件内容
            String subject = code + "是你的CatNote验证码";

            // 创建并配置邮件消息
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("holic512@163.com");
            helper.setTo(email);
            helper.setSubject(subject);

            // 创建 Thymeleaf 上下文并渲染模板
            Context context = new Context();
            context.setVariable("username", email);
            context.setVariable("verificationCode", code);

            // 针对不同用途,加载不同模板
            String template = switch (purpose) {
                case UserLogin -> "email/user/login.html";
                case UserRegister -> "email/user/register.html";
                case UserSetPassword -> "email/user/resetPassword.html";
                case AdminLogin -> "email/admin/login.html";
            };

            String htmlContent = templateEngine.process(template, context);

            // 设置邮件内容为 HTML 格式
            helper.setText(htmlContent, true);

            // 发送邮件
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }

    }


}
