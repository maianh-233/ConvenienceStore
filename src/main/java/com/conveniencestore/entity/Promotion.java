package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "promotions",
    indexes = {
        @Index(name = "idx_promotions_start_at", columnList = "start_at"),
        @Index(name = "idx_promotions_end_at", columnList = "end_at"),
        @Index(name = "idx_promotions_is_active", columnList = "is_active"),
        @Index(name = "idx_promotions_is_deleted", columnList = "is_deleted")
    }
)
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID khuyến mãi, tự tăng

    @Column(nullable = false, length = 150)
    private String name; // Tên khuyến mãi

    @Column(length = 50)
    private String type; // Loại khuyến mãi: 'percent', 'amount'

    @Column(precision = 12, scale = 2)
    private BigDecimal value = BigDecimal.ZERO; // Giá trị khuyến mãi

    @Column(name = "start_at")
    private LocalDateTime startAt; // Thời điểm bắt đầu

    @Column(name = "end_at")
    private LocalDateTime endAt;   // Thời điểm kết thúc

    @Column(name = "is_active")
    private int isActive = 1; // 1 = đang hoạt động, 0 = hết hạn / tạm dừng

    @Column(name = "is_deleted")
    private int isDeleted = 0; // Soft delete: 0 = chưa xóa, 1 = đã xóa

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt; // Thời điểm xóa soft delete

    @Column(length = 50)
    private String customerTier; // Loại khách hàng áp dụng: 'regular', 'vip', ...

    @Column(name = "max_uses")
    private Integer maxUses; // Số lần tối đa áp dụng khuyến mãi

    @Column(name = "min_order_amount", precision = 12, scale = 2)
    private BigDecimal minOrderAmount; // Giá trị đơn tối thiểu để áp dụng

    @Column(length = 500)
    private String note; // Ghi chú thêm

    @Column(name = "applicable_tiers", length = 50)
    private String applicableTiers; // "REGULAR,VIP"

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ================== Quan hệ ==================
    // 1 promotion có thể áp dụng cho nhiều đơn hàng
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    

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
