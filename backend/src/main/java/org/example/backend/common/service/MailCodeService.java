/**
 * CreateTime: 2024-08-29
 * Description: 邮箱工具类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.backend.common.dto.MailCodeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.example.backend.common.enums.MailCodePurpose;

@Component
public class MailCodeService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public MailCodeService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * @param mailCodeMessage 发送邮箱验证码的信息类
     * @return 发送状态
     */
    public boolean sendVerificationCode(MailCodeMessage mailCodeMessage) {
        try {

            String email = mailCodeMessage.getEmail();
            String code = mailCodeMessage.getCode();
            MailCodePurpose purpose = mailCodeMessage.getPurpose();

            // 创建邮件内容
            String subject = code + "是你的CatNote验证码";

            // 创建并配置邮件消息
            MimeMessage message = javaMailSender.createMimeMessage();
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
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }


}
