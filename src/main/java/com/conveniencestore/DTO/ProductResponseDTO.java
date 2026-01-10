package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductResponseDTO {

    private Long id;
    private String sku;
    private String barcode;
    private String productName;


    private Long categoryId;
    private String categoryName;

    private Long supplierId;
    private String supplierName;

    private Long unitId;
    private String unitName;

    private BigDecimal price;
    private BigDecimal cost;

    private String description;
    private String imageUrl;

    private int isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
