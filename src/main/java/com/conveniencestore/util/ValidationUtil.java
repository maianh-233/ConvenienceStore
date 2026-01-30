package com.conveniencestore.util;

import java.util.regex.Pattern;

public final class ValidationUtil {

    
    private ValidationUtil() {
    }

    /**
     * Regex kiểm tra Email cơ bản
     * - Cho phép chữ, số, dấu + _ . -
     * - Có ký tự @ và domain
     * Ví dụ hợp lệ: abc@gmail.com, test.user+1@company.vn
     */
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    /**
     * Regex kiểm tra số điện thoại Việt Nam
     * - Bắt đầu bằng 0 hoặc +84
     * - Đầu số hợp lệ: 3, 5, 7, 8, 9
     * - Tổng cộng 10 chữ số (nếu bắt đầu bằng 0)
     * Ví dụ hợp lệ: 0371234567, +84901234567
     */
    private static final Pattern PHONE_VN_PATTERN =
            Pattern.compile("^(0|\\+84)(3|5|7|8|9)[0-9]{8}$");

    /**
     * Regex kiểm tra mật khẩu mạnh
     * Yêu cầu:
     * - Ít nhất 8 ký tự
     * - Có ít nhất 1 chữ thường
     * - Có ít nhất 1 chữ hoa
     * - Có ít nhất 1 chữ số
     */
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");

    /* ==================================================
     * EMAIL
     * ==================================================
     */

    /**
     * Kiểm tra email có hợp lệ hay không
     *
     * @param email Chuỗi email cần kiểm tra
     * @return true nếu email hợp lệ, ngược lại false
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /* ==================================================
     * PHONE
     * ==================================================
     */

    /**
     * Kiểm tra số điện thoại Việt Nam
     *
     * @param phone Số điện thoại cần kiểm tra
     * @return true nếu số điện thoại hợp lệ, ngược lại false
     */
    public static boolean isValidPhoneVN(String phone) {
        if (phone == null || phone.isBlank()) {
            return false;
        }
        return PHONE_VN_PATTERN.matcher(phone.trim()).matches();
    }

    /* ==================================================
     * PASSWORD
     * ==================================================
     */

    /**
     * Kiểm tra mật khẩu có đủ mạnh hay không
     *
     * @param password Mật khẩu cần kiểm tra
     * @return true nếu mật khẩu hợp lệ theo tiêu chuẩn bảo mật
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
