package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderItemResponseDTO {

    private Long id;

    private Long orderId;

    private Long productId;
    private String productName;

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    private LocalDateTime createdAt;
}
