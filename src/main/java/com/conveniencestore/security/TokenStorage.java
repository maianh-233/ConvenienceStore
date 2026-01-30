package com.conveniencestore.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;
import java.util.Properties;

/**
 * TokenStorage
 * --------------------------------------------------------
 * Class chịu trách nhiệm:
 * - Lưu trữ Access Token và Refresh Token trên máy người dùng
 * - Mã hóa token bằng AES để tránh đọc trực tiếp từ file
 * - Hỗ trợ auto-login, logout
 *
 * Vị trí lưu:
 * - Thư mục HOME của người dùng
 * - File ẩn: .cs_store_config
 *
 * LƯU Ý:
 * - Token chỉ tồn tại ở máy local
 * - Refresh token đã được hash trong DB (2 lớp bảo mật)
 */
public final class TokenStorage {

    /**
     * Đường dẫn file lưu token (ẩn)
     */
    private static final Path TOKEN_FILE =
            Paths.get(System.getProperty("user.home"), ".cs_store_config");

    /**
     * Khóa mã hóa AES (16 ký tự = AES-128)
     * Lưu ý:
     * - Nên thay đổi theo dự án thực tế
     * - Không nên commit key thật lên repo public
     */
    private static final String ENCRYPTION_KEY = "StoreSecureKey12";

    /**
     * Thuật toán mã hóa sử dụng
     */
    private static final String ALGORITHM = "AES";

    private TokenStorage() {
        // Không cho phép tạo instance
    }

    /* ==================================================
     * SAVE TOKEN
     * ==================================================
     */

    /**
     * Lưu cả Access Token và Refresh Token vào file
     * Token được mã hóa trước khi ghi ra ổ đĩa
     *
     * @param accessToken  JWT Access Token
     * @param refreshToken JWT Refresh Token
     */
    public static void saveTokens(String accessToken, String refreshToken) {
        try {
            Properties props = new Properties();
            props.setProperty("access", encrypt(accessToken));
            props.setProperty("refresh", encrypt(refreshToken));

            // Ghi file dạng key=value (giống file cấu hình)
            try (var writer = Files.newBufferedWriter(TOKEN_FILE)) {
                props.store(writer, "CS-Store Token Storage");
            }

        } catch (Exception e) {
            System.err.println("Không thể lưu token: " + e.getMessage());
        }
    }

    /**
     * Lưu riêng Access Token
     * - Giữ nguyên Refresh Token (nếu đã tồn tại)
     * - Dùng cho auto-login hoặc refresh token
     *
     * @param accessToken JWT Access Token
     */
    public static void saveAccessToken(String accessToken) {
        try {
            Properties props = new Properties();

            // Nếu file đã tồn tại → load để không mất refresh token
            if (Files.exists(TOKEN_FILE)) {
                try (var input = Files.newInputStream(TOKEN_FILE)) {
                    props.load(input);
                }
            }

            props.setProperty("access", encrypt(accessToken));

            try (var writer = Files.newBufferedWriter(TOKEN_FILE)) {
                props.store(writer, "CS-Store Token Storage");
            }

        } catch (Exception e) {
            System.err.println("Không thể lưu access token: " + e.getMessage());
        }
    }


    /* ==================================================
     * GET TOKEN
     * ==================================================
     */

    /**
     * Lấy Access Token từ file lưu trữ
     *
     * @return Access Token đã được giải mã, hoặc null nếu không tồn tại
     */
    public static String getAccessToken() {
        return getProperty("access");
    }

    /**
     * Lấy Refresh Token từ file lưu trữ
     *
     * @return Refresh Token đã được giải mã, hoặc null nếu không tồn tại
     */
    public static String getRefreshToken() {
        return getProperty("refresh");
    }

    /**
     * Đọc và giải mã một giá trị token theo key
     */
    private static String getProperty(String key) {
        try {
            if (!Files.exists(TOKEN_FILE)) {
                return null;
            }

            Properties props = new Properties();
            try (var input = Files.newInputStream(TOKEN_FILE)) {
                props.load(input);
            }

            String encryptedValue = props.getProperty(key);
            return (encryptedValue != null)
                    ? decrypt(encryptedValue)
                    : null;

        } catch (Exception e) {
            return null;
        }
    }

    /* ==================================================
     * CLEAR TOKEN
     * ==================================================
     */

    /**
     * Xóa toàn bộ token đã lưu (Logout)
     * - Xóa file lưu trữ token trên máy người dùng
     */
    public static void clear() {
        try {
            Files.deleteIfExists(TOKEN_FILE);
        } catch (Exception e) {
            System.err.println("Không thể xóa token: " + e.getMessage());
        }
    }

    /* ==================================================
     * AES ENCRYPT / DECRYPT
     * ==================================================
     */

    /**
     * Mã hóa chuỗi bằng AES
     *
     * @param data Chuỗi gốc
     * @return Chuỗi đã được mã hóa và encode Base64
     */
    private static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey =
                new SecretKeySpec(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * Giải mã chuỗi AES
     *
     * @param encryptedData Chuỗi đã mã hóa Base64
     * @return Chuỗi gốc sau khi giải mã
     */
    private static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey =
                new SecretKeySpec(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decoded), StandardCharsets.UTF_8);
    }
}
