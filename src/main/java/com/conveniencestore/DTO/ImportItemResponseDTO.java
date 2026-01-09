package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ImportItemResponseDTO {

    private Long id;

    private Long importId;

    private Long productId;
    private String productName;

    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
