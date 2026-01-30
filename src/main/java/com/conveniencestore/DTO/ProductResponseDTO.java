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

    // Láy thông tin tồn kho
    private Integer quantity;

    private LocalDateTime inventoryUpdatedAt;


    public ProductResponseDTO(
            Long id,
            String sku,
            String barcode,
            String productName,
            Long categoryId,
            String categoryName,
            Long supplierId,
            String supplierName,
            Long unitId,
            String unitName,
            BigDecimal price,
            BigDecimal cost,
            String description,
            String imageUrl,
            Integer isActive,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Integer quantity,
            LocalDateTime inventoryUpdatedAt
    ) {
        this.id = id;
        this.sku = sku;
        this.barcode = barcode;
        this.productName = productName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.unitId = unitId;
        this.unitName = unitName;
        this.price = price;
        this.cost = cost;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.quantity = quantity;
        this.inventoryUpdatedAt = inventoryUpdatedAt;
    }

}
