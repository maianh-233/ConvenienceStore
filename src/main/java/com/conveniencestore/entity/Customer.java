package com.conveniencestore.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.conveniencestore.constant.CustomerTier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

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
    private Long id;

    @Column(nullable = false, length = 150)
    private String fullName;

    private LocalDate dateOfBirth;

    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 50, unique = true)
    private String phone;

    @Column(length = 500)
    private String address;

    /**
     * 0 = Nam
     * 1 = Nữ
     */
    @Column(nullable = false)
    private int gender;

    /* =================== HẠNG & ĐIỂM =================== */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CustomerTier tier = CustomerTier.REGULAR;

    @Column(nullable = false)
    private int points = 0;

    /* =================== MẬT KHẨU =================== */
    @Column(length = 255)
    private String password;

    /* =================== SOFT DELETE =================== */
    @Column(name = "is_deleted",nullable = false)
    private int isDeleted = 1;

    /* =================== AUDIT =================== */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
