/**
 * CreateTime: 2024-08-29
 * Description: 邮箱工具类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.example.backend.enums.VerificationCodePurpose;

@Component
public class MailUtil {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public MailUtil(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * @param toEmail          发送邮箱
     * @param verificationCode 验证码
     * @param use              用途
     * @throws MessagingException 邮箱错误
     */
    public void sendVerificationCode(String toEmail, String verificationCode, VerificationCodePurpose use) throws MessagingException {
        // 创建邮件内容
        String subject = verificationCode + "是你的CatNote验证码";

        // 创建并配置邮件消息
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("holic512@163.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);

        // 创建 Thymeleaf 上下文并渲染模板
        Context context = new Context();
        context.setVariable("username", toEmail);
        context.setVariable("verificationCode", verificationCode);

        // 针对不同用途,加载不同模板
        String template = switch (use) {
            case UserLogin -> "email/user/login.html";
            case UserRegister -> "email/user/register.html";
            case UserSetPassword -> "email/user/resetPassword.html";
            default -> "email/user/404.html";
        };

        String htmlContent = templateEngine.process(template, context);

        // 设置邮件内容为 HTML 格式
        helper.setText(htmlContent, true);

        // 发送邮件
        javaMailSender.send(message);
    }


}
