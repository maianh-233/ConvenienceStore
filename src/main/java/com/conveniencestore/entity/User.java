package com.conveniencestore.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Index;



@Entity
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_users_created_id", columnList = "created_at DESC, id DESC"),
        @Index(name = "idx_users_active", columnList = "active"),
        @Index(name = "idx_users_role", columnList = "role_id")
    }
)
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "fullName", length = 255, nullable = false)
    private String fullName;

    private LocalDate dateOfBirth;

    @Column(length = 255, unique = true, nullable = false)
    private String email;
    @Column(length = 50, unique = true, nullable = false)
    private String phone;

    private String address;
    
    @Column(name = "img_url")
    private String imgUrl;

    // Thêm thuộc tính để có thể xóa file trên cloudinary
    @Column(name = "img_urlID")
    private String imgUrlID;


    private int gender;

    // ================== ROLE ==================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false)
    private int active = 1;
  
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

    @Column(name = "refresh_token_hash", length = 512)
    private String refreshTokenHash;
    private LocalDateTime refreshTokenExpiry;

    @Column(name = "reset_password_token_hash", length = 512)
    private String resetPasswordTokenHash;
    private LocalDateTime resetPasswordTokenExpiry;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
