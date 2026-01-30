package com.conveniencestore.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    // 1. Thêm một chuỗi Salt cố định (Muối) 
    // Giúp mật khẩu "123456" sau khi băm sẽ không giống với các chuỗi SHA-256 phổ biến trên mạng
    private static final String SALT = "ConvenienceStore_Secure_Project_2026";

    public static String sha256(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        try {
            // 2. Kết hợp Input với Salt
            String saltedInput = input + SALT;

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // 3. Sử dụng StandardCharsets.UTF_8 để tránh lỗi font chữ/ký tự đặc biệt
            byte[] bytes = md.digest(saltedInput.getBytes(StandardCharsets.UTF_8));
            
            // 4. Tối ưu việc chuyển đổi sang Hex bằng StringBuilder
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                // Chuyển byte sang hex string 2 ký tự
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Log lỗi nếu thuật toán không tồn tại trong hệ thống
            System.err.println("Lỗi thuật toán hash: " + e.getMessage());
            return null;
        }
    }
}