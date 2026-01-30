package com.conveniencestore.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "suppliers",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_suppliers_email", columnNames = "email"),
        @UniqueConstraint(name = "ux_suppliers_phone", columnNames = "phone")
    }
)
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID nhà cung cấp, tự tăng

    @Column(nullable = false, length = 150)
    private String name; // Tên nhà cung cấp

    @Column(length = 255, unique = true)
    private String email; // Email liên hệ

    @Column(length = 50, unique = true)
    private String phone; // Số điện thoại liên hệ

    @Column(length = 500)
    private String address; // Địa chỉ nhà cung cấp

    @Column(length = 500)
    private String note; // Ghi chú thêm nếu cần

    @Column(name="created_at")
    private LocalDateTime createdAt;
    
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="is_deleted",nullable = false)
    private int isDeleted = 1; 

    /* =================== QUAN HỆ VỚI PRODUCT =================== */
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<Product> products; // Danh sách sản phẩm từ nhà cung cấp này

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
