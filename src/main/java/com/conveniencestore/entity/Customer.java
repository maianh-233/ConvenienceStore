package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.conveniencestore.constant.CustomerTier;

@Entity
@Table(
    name = "customers",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_customers_email", columnNames = "email"),
        @UniqueConstraint(name = "ux_customers_phone", columnNames = "phone")
    }
)
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID khách hàng, tự tăng

    @Column(nullable = false, length = 150)
    private String fullName; // Họ và tên khách hàng

    private LocalDate dateOfBirth; // Ngày sinh

    @Column(length = 255, unique = true)
    private String email; // Email liên hệ (duy nhất)

    @Column(length = 50, unique = true)
    private String phone; // Số điện thoại (duy nhất)

    @Column(length = 500)
    private String address; // Địa chỉ đầy đủ

    private int gender; // Giới tính: 0=Nam, 1=Nữ

    /* =================== HẠNG VÀ ĐIỂM =================== */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CustomerTier tier = CustomerTier.REGULAR; // Hạng khách hàng

    private int points = 0; // Điểm tích lũy

    /* =================== MẬT KHẨU =================== */
    @Column(length = 255, nullable = true)
    private String password; // Mật khẩu (nên lưu hashed)

     /* =================== SOFT DELETE =================== */
    @Column(nullable = false)
    private int isDeleted = 1; // Đánh dấu khách hàng đã bị xóa (soft delete)

    /* =================== THÔNG TIN AUDIT =================== */
    private LocalDateTime createdAt; // Thời điểm thêm khách hàng
    private LocalDateTime updatedAt; // Thời điểm cập nhật gần nhất

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
