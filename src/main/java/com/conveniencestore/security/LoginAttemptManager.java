package com.conveniencestore.security;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAttemptManager {

    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_MINUTES = 1;

    private static final Map<String, AttemptInfo> CACHE =
            new ConcurrentHashMap<>();

    private static class AttemptInfo {
        int attempts;
        LocalDateTime lockedUntil;
    }

    /* Kiểm tra trước khi login */
    public static void checkLocked(String username) {
        AttemptInfo info = CACHE.get(username);
        if (info != null && info.lockedUntil != null &&
            LocalDateTime.now().isBefore(info.lockedUntil)) {

            throw new RuntimeException(
                "Tài khoản tạm thời bị khóa. Vui lòng thử lại sau "
                + LOCK_MINUTES + " phút");
        }
    }

    /* Gọi khi đăng nhập sai */
    public static void onFail(String username) {
        AttemptInfo info = CACHE.computeIfAbsent(
                username, k -> new AttemptInfo());

        info.attempts++;

        if (info.attempts >= MAX_ATTEMPTS) {
            info.lockedUntil = LocalDateTime.now()
                    .plusMinutes(LOCK_MINUTES);
        }
    }

    /* Gọi khi đăng nhập đúng */
    public static void onSuccess(String username) {
        CACHE.remove(username);
    }

    public static String getLockMessage(String username) {
        AttemptInfo info = CACHE.get(username);
        if (info == null) return null;

        if (info.lockedUntil != null &&
            LocalDateTime.now().isBefore(info.lockedUntil)) {

            long secondsLeft = java.time.Duration
                    .between(LocalDateTime.now(), info.lockedUntil)
                    .getSeconds();

            return "Bạn đã nhập sai quá 3 lần. "
                + "Tài khoản tạm thời bị khóa trong "
                + (secondsLeft / 60 + 1) + " phút.";
        }
        return null;
    }

}
