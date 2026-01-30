package com.conveniencestore.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public class JwtUtil {

    private static final String SECRET_STRING = "Dua_Con_Tinh_Than_ConvenienceStore_2024_Secure";
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    private static final long ACCESS_EXPIRE = 15 * 60 * 1000; // 15 phút
    private static final long REFRESH_EXPIRE = 7 * 24 * 60 * 60 * 1000; // 7 ngày

    // 1. Tạo Access Token (Nên có cả UserId và Role để kiểm tra quyền nhanh)
    public static String generateAccessToken(Long userId, Long role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRE))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. Tạo Refresh Token
    public static String generateRefreshToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRE))
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 3. Hàm dùng chung để lấy thông tin từ Token
    private static Claims getAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims(); // Vẫn lấy được thông tin dù đã hết hạn
        } catch (Exception e) {
            System.err.println("Lỗi Token: " + e.getMessage());
            return null;
        }
    }

    public static Long getUserId(String token) {
        Claims claims = getAllClaims(token);
        return (claims != null) ? Long.parseLong(claims.getSubject()) : null;
    }

    public static Integer getUserRole(String token) {
        Claims claims = getAllClaims(token);
        return (claims != null) ? claims.get("role", Integer.class) : null;
    }

    // 4. Kiểm tra trạng thái Token
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return true;
        }
    }

    // 5. Làm mới Access Token (Phía Backend gọi khi Frontend gửi Refresh Token lên)
    public static String refreshAccessToken(String refreshToken, Long role) {
        Long userId = getUserId(refreshToken);
        if (userId != null && !isTokenExpired(refreshToken)) {
            return generateAccessToken(userId, role);
        }
        return null;
    }
}