package com.conveniencestore.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_users_username", columnNames = "username"),
        @UniqueConstraint(name = "ux_users_email", columnNames = "email"),
        @UniqueConstraint(name = "ux_users_phone", columnNames = "phone")
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

    private String fullName;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String identityNumber;
    private String address;
    private String img_url;
    private int gender;

    // ================== ROLE ==================
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    private int active = 1;
    private int locked = 0;

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
