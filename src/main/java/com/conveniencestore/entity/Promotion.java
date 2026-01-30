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
        @Index(name = "idx_promotions_is_active", columnList = "is_active")
    }
)
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PromotionType type;

    @Column(precision = 12, scale = 2)
    private BigDecimal value = BigDecimal.ZERO;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "is_active", nullable = false)
    private int isActive = 1;

    /* ===== FIX DUY NHẤT ===== */
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private CustomerTier customerTier;

    @Column(name = "max_uses")
    private Integer maxUses;

    @Column(name = "min_order_amount", precision = 12, scale = 2)
    private BigDecimal minOrderAmount;

    @Column(length = 500)
    private String note;

      
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
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
