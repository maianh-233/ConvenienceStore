package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.conveniencestore.constant.PaymentMethod;
import com.conveniencestore.constant.PaymentStatus;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 1 order chỉ có 1 payment

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO; // Số tiền thanh toán

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethod method = PaymentMethod.CASH; // Phương thức thanh toán

    @Column(name = "transaction_ref", length = 255)
    private String transactionRef; // mã giao dịch (nếu có)

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING; // Trạng thái thanh toán

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
