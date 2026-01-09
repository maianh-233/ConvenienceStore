package com.conveniencestore.DTO;

import com.conveniencestore.constant.PaymentMethod;
import com.conveniencestore.constant.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequestDTO {

    private Long id;          // null = create, có = update
    private Long orderId;     // dùng cho map đầy đủ

    private BigDecimal amount;
    private PaymentMethod method;
    private String transactionRef;
    private PaymentStatus status;
}
