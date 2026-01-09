package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ImportItemRequestDTO {

    private Long id;         // null = create, có = update
    private Long importId;   // dùng khi map đầy đủ
    private Long productId;

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
