package com.conveniencestore.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MailTemplate {

    // 1. Template cho OTP (Dùng khi quên mật khẩu hoặc xác nhận thanh toán)
    public static String otpTemplate(String userName, String otp) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));
        
        return """
            <div style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; max-width: 500px; margin: 20px auto; border: 1px solid #e0e0e0; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 10px rgba(0,0,0,0.05);">
                <div style="background-color: #16a34a; padding: 25px; text-align: center;">
                    <h1 style="color: #ffffff; margin: 0; font-size: 24px;">Xác Thực Tài Khoản</h1>
                </div>
                
                <div style="padding: 30px; line-height: 1.6; color: #333333;">
                    <p>Xin chào <strong>%s</strong>,</p>
                    <p>Bạn đã yêu cầu mã xác thực cho tài khoản tại <b>Convenience Store</b>. Vui lòng sử dụng mã OTP dưới đây:</p>
                    
                    <div style="text-align: center; margin: 30px 0;">
                        <span style="display: inline-block; padding: 15px 30px; background-color: #f0fdf4; border: 2px dashed #16a34a; border-radius: 8px; font-size: 32px; font-weight: bold; color: #16a34a; letter-spacing: 5px;">
                            %s
                        </span>
                    </div>
                    
                    <p style="font-size: 14px; color: #666666;">
                        * Mã này có hiệu lực trong <b>5 phút</b>.<br/>
                        * Thời gian yêu cầu: %s
                    </p>
                </div>
                
                <div style="background-color: #f9fafb; padding: 20px; text-align: center; border-top: 1px solid #eeeeee;">
                    <p style="margin: 0; font-size: 12px; color: #9ca3af;">
                        Đây là email tự động, vui lòng không phản hồi.<br/>
                        &copy; 2026 Convenience Store System. All rights reserved.
                    </p>
                </div>
            </div>
            """.formatted(userName, otp, currentTime);
    }
}