package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "inventory",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_inventory_product", columnNames = "product_id")
    }
)
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID inventory, tự tăng

    // ================== QUAN HỆ VỚI PRODUCT ==================
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // 1 product chỉ có 1 inventory

    @Column(nullable = false)
    private int quantity = 0; // Số lượng hiện tại

    private LocalDateTime lastCheckedAt; // Lần kiểm tra tồn kho gần nhất

    private LocalDateTime updatedAt; // Thời điểm cập nhật tồn kho

    // ================== TỰ ĐỘNG SET THỜI GIAN ==================
    @PrePersist
    void onCreate() {
        updatedAt = LocalDateTime.now();
        if (lastCheckedAt == null) lastCheckedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
