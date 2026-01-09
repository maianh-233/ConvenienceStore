package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {

    private Long id;

    private String sku;
    private String productName;

    private Long categoryId;
    private Long supplierId;
    private Long unitId;

    private BigDecimal price;
    private BigDecimal cost;

    private String description;
    private String imageUrl;

    private boolean isActive;
}
