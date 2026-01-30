package com.conveniencestore.bus;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

import com.conveniencestore.DTO.ImageUploadResultDTO;
import com.conveniencestore.DTO.UserDTO;
import com.conveniencestore.DTO.UserRequestDTO;
import com.conveniencestore.dao.AuthDAO;
import com.conveniencestore.dao.UserDAO;
import com.conveniencestore.entity.User;
import com.conveniencestore.security.AuthContext;
import com.conveniencestore.security.JwtUtil;
import com.conveniencestore.security.LoginAttemptManager;
import com.conveniencestore.security.TokenStorage;
import com.conveniencestore.service.ImageUploadService;
import com.conveniencestore.util.HashUtil;
import com.conveniencestore.util.MailTemplate;
import com.conveniencestore.util.MailUtil;
import com.conveniencestore.util.ValidationUtil;
import java.io.File;


public class AuthBUS {
    private final AuthDAO authDAO = new AuthDAO();
    private final UserDAO userDAO = new UserDAO();

    /* ================= LOGIN ================= */
    public String login(String username, String password) {

        // 1. Check khóa tạm (RAM)
        String lockMsg = LoginAttemptManager.getLockMessage(username);
        if (lockMsg != null) {
            throw new RuntimeException(lockMsg);
        }

        User user = null;

        try {
            // Tìm user
            user = authDAO.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("INVALID"));
        } catch (RuntimeException ex) {
            // Sai username → vẫn tính là sai 1 lần
            LoginAttemptManager.onFail(username);

            String msg = LoginAttemptManager.getLockMessage(username);
            if (msg != null) {
                throw new RuntimeException(msg);
            }

            // Không tiết lộ user tồn tại hay không
            throw new RuntimeException("Tên đăng nhập không chính xác");
        }

        // 2. Kiểm tra trạng thái tài khoản
        if (user.getActive() == 0) {
            throw new RuntimeException("Tài khoản đã bị khóa vui lòng gặp Admin giải quyết");
        }

        // 3. Kiểm tra chức vụ này có bị khóa hay không
        if (user.getRole().getIsActive() == 0) {
            throw new RuntimeException("Chức vụ này  đã bị chặn không được đăng nhập");
        }

        // 4. Kiểm tra mật khẩu
        if (!BCrypt.checkpw(password, user.getPasswordHash())) {

            LoginAttemptManager.onFail(username);

            String msg = LoginAttemptManager.getLockMessage(username);
            if (msg != null) {
                throw new RuntimeException(msg);
            }

            throw new RuntimeException("Mật khẩu không chính xác");
        }

        // 5. Login đúng → reset counter
        LoginAttemptManager.onSuccess(username);

        // Tạo bộ đôi Token (Dùng hàm generate đã sửa có kèm Role)
        String accessToken = JwtUtil.generateAccessToken(user.getId(), user.getRole().getId());
        String refreshToken = JwtUtil.generateRefreshToken(user.getId());

        // Lưu vết Refresh Token (Băm trước khi lưu để bảo mật DB)
        user.setRefreshTokenHash(HashUtil.sha256(refreshToken));
        user.setRefreshTokenExpiry(LocalDateTime.now().plusDays(7));
        user.setLastLogin(LocalDateTime.now());

        if (!authDAO.update(user)) {
            throw new RuntimeException("Lỗi hệ thống khi cập nhật thông tin đăng nhập");
        }

        // Lưu Refresh Token xuống ổ cứng máy tính
        TokenStorage.saveTokens(accessToken, refreshToken);

        // Thiết lập ngữ cảnh người dùng hiện tại
        AuthContext.set(user);

        return accessToken;
    }

    /* ================= AUTO LOGIN ================= */
    public User autoLogin() {
        String refreshToken = TokenStorage.getRefreshToken();
        if (refreshToken == null)
            return null;

        String hash = HashUtil.sha256(refreshToken);
        Optional<User> userOpt = authDAO.findByRefreshTokenHash(hash);

        if (userOpt.isEmpty())
            return null;

        User user = userOpt.get();

        // User bị khóa
        if (user.getActive() == 0) {
            // Xóa thông tin token cũ trong DB
            user.setRefreshTokenHash(null);
            user.setRefreshTokenExpiry(null);
            authDAO.update(user);

            // Xóa token trên ổ cứng
            TokenStorage.clear();
            AuthContext.clear();
            return null;
        }

        // Role bị khóa
        if (user.getRole() == null || user.getRole().getIsActive() == 0) {
            // Xóa thông tin token cũ trong DB
            user.setRefreshTokenHash(null);
            user.setRefreshTokenExpiry(null);
            authDAO.update(user);

            // Xóa token trên ổ cứng
            TokenStorage.clear();
            AuthContext.clear();
            return null;
        }

        // Kiểm tra hết hạn của Token trong Database
        if (user.getRefreshTokenExpiry().isBefore(LocalDateTime.now())) {
            // Xóa thông tin token cũ trong DB
            user.setRefreshTokenHash(null);
            user.setRefreshTokenExpiry(null);
            authDAO.update(user);

            // Xóa token trên ổ cứng
            TokenStorage.clear();
            AuthContext.clear();
            return null;
        }
        String newAccessToken = JwtUtil.generateAccessToken(
                user.getId(),
                user.getRole().getId());
        TokenStorage.saveAccessToken(newAccessToken);

        AuthContext.set(user);
        return user;
    }

    /* ================= LOGOUT ================= */
    public void logout() {
        UserDTO currentUserDTO = AuthContext.get();
        User currentUser = authDAO.findById(currentUserDTO.getId()).orElse(null);
        if (currentUser != null) {
            currentUser.setRefreshTokenHash(null);
            currentUser.setRefreshTokenExpiry(null);
            authDAO.update(currentUser);
        }
        TokenStorage.clear();
        AuthContext.clear();
    }

    /* ================= FORGOT PASSWORD ================= */
    public void sendOtp(String email) {
        User user = authDAO.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email này chưa được đăng ký trong hệ thống"));

        // Tạo mã OTP 6 số
        String otp = String.format("%06d", new Random().nextInt(1000000));

        // Lưu mã OTP đã băm vào DB để đối chiếu sau
        user.setResetPasswordTokenHash(HashUtil.sha256(otp));
        user.setResetPasswordTokenExpiry(LocalDateTime.now().plusMinutes(5));

        if (authDAO.update(user)) {
            String htmlContent = MailTemplate.otpTemplate(user.getFullName(), otp);
            MailUtil.sendHtmlAsync(user.getEmail(), "[CS-Store] Mã xác thực đặt lại mật khẩu", htmlContent);
        }
    }

    /* ================= VERIFY OTP ================= */
    public User verifyOtp(String email, String otp) {
        User user = authDAO.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin yêu cầu"));

        // 1. Chưa yêu cầu OTP hoặc đã dùng
        if (user.getResetPasswordTokenHash() == null ||
                user.getResetPasswordTokenExpiry() == null) {
            throw new RuntimeException("OTP không hợp lệ hoặc đã được sử dụng");
        }

        // 2. Hết hạn
        if (user.getResetPasswordTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Mã xác thực đã hết hạn");
        }

        // 3. So sánh OTP
        String inputHash = HashUtil.sha256(otp.trim());
        if (!inputHash.equals(user.getResetPasswordTokenHash())) {
            throw new RuntimeException("Mã xác thực không chính xác");
        }

        authDAO.update(user);

        return user;
    }

    public void resetPassword(String email, String otp, String newPassword) {
        // 1. Kiểm tra OTP
        User user = verifyOtp(email, otp);

        // 2. Đổi mật khẩu
        user.setPasswordHash(BCrypt.hashpw(newPassword, BCrypt.gensalt()));

        // 3. Xóa OTP
        user.setResetPasswordTokenHash(null);
        user.setResetPasswordTokenExpiry(null);

        // 4. Ghi DB
        if (!authDAO.update(user)) {
            throw new RuntimeException("Không thể cập nhật mật khẩu mới");
        }
    }

    // Update thông tin
    public boolean updateUserInfo(UserRequestDTO dto) {

        UserDTO currentUser = AuthContext.get();
        if (currentUser == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        if (!currentUser.getId().equals(dto.getId())) {
            throw new RuntimeException("Không có quyền chỉnh sửa thông tin người khác");
        }

        // ===== Email =====
        if (!currentUser.getEmail().equals(dto.getEmail())) {
            if (!ValidationUtil.isValidEmail(dto.getEmail())) {
                throw new RuntimeException("Mail không hợp lệ");
            }
            if (userDAO.existsByEmail(dto.getEmail())) {
                throw new RuntimeException("Mail đã có người sử dụng");
            }
        }

        // ===== Phone =====
        if (!currentUser.getPhone().equals(dto.getPhone())) {
            if (!ValidationUtil.isValidPhoneVN(dto.getPhone())) {
                throw new RuntimeException("Số điện thoại không hợp lệ");
            }
            if (userDAO.existsByPhone(dto.getPhone())) {
                throw new RuntimeException("Số điện thoại đã có người sử dụng");
            }
        }

        // ===== Password =====
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (!ValidationUtil.isValidPassword(dto.getPassword())) {
                throw new RuntimeException("Mật khẩu không đúng định dạng");
            }
            dto.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        } else {
            dto.setPassword(null);
        }

        User updatedUser = authDAO.updateUserInfo(dto);
        if (updatedUser == null)
            return false;

        AuthContext.set(updatedUser);
        return true;
    }

    public boolean updateUserAvatar(File avatarFile) {

        UserDTO currentUser = AuthContext.get();
        if (currentUser == null) {
            throw new RuntimeException("Chưa đăng nhập");
        }

        ImageUploadService imageService = new ImageUploadService();

        ImageUploadResultDTO uploadResult = null;

        try {
            //UPLOAD ẢNH MỚI TRƯỚC
            uploadResult = imageService.upload(avatarFile);

            // UPDATE DB
            User updatedUser = authDAO.updateUserAvatar(
                    currentUser.getId(),
                    uploadResult.getUrl(),
                    uploadResult.getPublicId());

            if (updatedUser == null) {
                // rollback cloud
                imageService.delete(uploadResult.getPublicId());
                return false;
            }

            // XÓA ẢNH CŨ (SAU KHI DB OK)
            if (currentUser.getImgUrlID() != null) {
                imageService.delete(currentUser.getImgUrlID());
            }

            AuthContext.set(updatedUser);
            return true;

        } catch (Exception e) {

            // rollback cloud nếu upload xong mà lỗi
            if (uploadResult != null) {
                imageService.delete(uploadResult.getPublicId());
            }

            throw e;
        }
    }

}