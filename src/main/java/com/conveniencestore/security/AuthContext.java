package com.conveniencestore.security;

import com.conveniencestore.DTO.UserDTO;
import com.conveniencestore.entity.User;
import com.conveniencestore.mapper.UserMapper;

public class AuthContext {
    private static UserDTO currentUser;

    private AuthContext() {}

    /**
     * Thiết lập thông tin người dùng hiện tại (chuyển Entity thành DTO)
     */
    public static void set(User user) {
        if (user != null) {
            currentUser = UserMapper.toDTO(user);
        } else {
            currentUser = null;
        }
    }

    /**
     * Lấy thông tin người dùng hiện tại (dạng DTO)
     */
    public static UserDTO get() {
        return currentUser;
    }

    /**
     * Kiểm tra người dùng đã đăng nhập chưa
     */
    public static boolean isAuthenticated() {
        return currentUser != null;
    }

    /**
     * Xóa thông tin người dùng (đăng xuất)
     */
    public static void clear() {
        currentUser = null;
    }
}