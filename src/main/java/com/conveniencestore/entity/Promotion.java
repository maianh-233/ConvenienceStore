package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.conveniencestore.constant.CustomerTier;
import com.conveniencestore.constant.PromotionType;
@Entity
@Table(
    name = "promotions",
    indexes = {
        @Index(name = "idx_promotions_start_at", columnList = "start_at"),
        @Index(name = "idx_promotions_end_at", columnList = "end_at"),
        @Index(name = "idx_promotions_is_active", columnList = "is_active"),

    }
)
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID khuyến mãi, tự tăng

    // ================== Thông tin cơ bản ==================
    @Column(nullable = false, unique = true, length = 50)
    private String code;
    //CODE Khuyến mãi, định danh duy nhất

    @Column(nullable = false, length = 150)
    private String name; // Tên khuyến mãi

    /** Loại khuyến mãi */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PromotionType type;

    @Column(precision = 12, scale = 2)
    private BigDecimal value = BigDecimal.ZERO; // Giá trị khuyến mãi

    @Column(name = "start_at")
    private LocalDateTime startAt; // Thời điểm bắt đầu

    @Column(name = "end_at")
    private LocalDateTime endAt;   // Thời điểm kết thúc

    @Column(name = "is_active", nullable = false)
    private int isActive = 1; // 1 = đang hoạt động, 0 = hết hạn / tạm dừng



    @Column(length = 50)
    private CustomerTier customerTier; // Loại khách hàng áp dụng: 'regular', 'vip', ...

    @Column(name = "max_uses")
    private Integer maxUses; // Số lần tối đa áp dụng khuyến mãi

    @Column(name = "min_order_amount", precision = 12, scale = 2)
    private BigDecimal minOrderAmount; // Giá trị đơn tối thiểu để áp dụng

    @Column(length = 500)
    private String note; // Ghi chú thêm

  

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
