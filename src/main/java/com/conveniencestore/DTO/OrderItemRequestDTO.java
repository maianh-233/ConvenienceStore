package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemRequestDTO {

    private Long id;          // null = create, có = update
    private Long orderId;     // dùng cho map phức tạp
    private Long productId;

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
