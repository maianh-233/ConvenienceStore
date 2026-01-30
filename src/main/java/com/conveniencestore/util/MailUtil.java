package com.conveniencestore.util;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conveniencestore.config.MailConfig;

import jakarta.mail.*;
import jakarta.mail.internet.*;

public final class MailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailUtil.class);
    
    // 1. Tạo một luồng riêng để gửi email (không làm treo giao diện Swing)
    private static final ExecutorService EMAIL_EXECUTOR = Executors.newFixedThreadPool(2);

    private MailUtil() {}

    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", MailConfig.HOST);
        props.put("mail.smtp.port", String.valueOf(MailConfig.PORT));
        props.put("mail.smtp.ssl.trust", MailConfig.HOST);
        // Tối ưu: Thêm timeout để không chờ đợi vô hạn nếu mạng lỗi
        props.put("mail.smtp.connectiontimeout", "5000"); 
        props.put("mail.smtp.timeout", "5000");

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        MailConfig.USERNAME,
                        MailConfig.APP_PASSWORD
                );
            }
        });
    }

    /**
     * Gửi mail HTML Bất đồng bộ (Async) - Khuyên dùng cho Swing
     */
    public static void sendHtmlAsync(String to, String subject, String html) {
        EMAIL_EXECUTOR.submit(() -> {
            try {
                send(to, subject, html, true);
            } catch (Exception e) {
                LOGGER.error("Gửi mail thất bại trong luồng nền tới: {}", to, e);
            }
        });
    }

    public static void sendText(String to, String subject, String content) {
        send(to, subject, content, false);
    }

    public static void sendHtml(String to, String subject, String html) {
        send(to, subject, html, true);
    }

    private static void send(String to, String subject, String content, boolean isHtml) {
        try {
            Message message = new MimeMessage(createSession());

            message.setFrom(new InternetAddress(
                    MailConfig.USERNAME,
                    MailConfig.FROM_NAME
            ));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);

            if (isHtml) {
                message.setContent(content, "text/html; charset=UTF-8");
            } else {
                message.setText(content);
            }

            Transport.send(message);
            LOGGER.info("Mail gửi thành công tới {}", to);

        } catch (Exception e) {
            LOGGER.error("Lỗi khi gửi mail tới {}", to, e);
            
        }
    }
    
    // Đảm bảo đóng executor khi tắt ứng dụng
    public static void shutdown() {
        EMAIL_EXECUTOR.shutdown();
    }
}