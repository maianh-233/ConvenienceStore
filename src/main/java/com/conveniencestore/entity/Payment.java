package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "payments",
    indexes = {
        @Index(name = "idx_payments_order", columnList = "order_id")
    }
)
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID thanh toán, tự tăng

    // ================== QUAN HỆ VỚI ORDER ==================
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 1 order chỉ có 1 payment

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO; // Số tiền thanh toán

    @Column(length = 50, nullable = false)
    private String method = "cash"; // cash, card, momo, v.v.

    @Column(name = "transaction_ref", length = 255)
    private String transactionRef; // mã giao dịch (nếu có)

    @Column(length = 50)
    private String status = "pending"; // completed, pending, failed, etc.

    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
