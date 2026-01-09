package com.conveniencestore.DTO;

import com.conveniencestore.constant.PaymentMethod;
import com.conveniencestore.constant.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentResponseDTO {

    private Long id;
    private Long orderId;

    private BigDecimal amount;
    private PaymentMethod method;
    private String transactionRef;
    private PaymentStatus status;

    private LocalDateTime createdAt;
}
