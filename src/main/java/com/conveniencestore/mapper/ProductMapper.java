package com.conveniencestore.mapper;

import com.conveniencestore.DTO.ProductRequestDTO;
import com.conveniencestore.DTO.ProductResponseDTO;
import com.conveniencestore.entity.Category;
import com.conveniencestore.entity.Product;
import com.conveniencestore.entity.Supplier;
import com.conveniencestore.entity.Unit;

public class ProductMapper {
    
    public static Product toEntityVer2(ProductRequestDTO dto) {
        Product p = new Product();

        p.setId(dto.getId()); // null = create, có = update
        p.setSku(dto.getSku());
        p.setProductName(dto.getProductName());

        p.setPrice(dto.getPrice());
        p.setCost(dto.getCost());
        p.setDescription(dto.getDescription());
        p.setImageUrl(dto.getImageUrl());
        p.setActive(dto.isActive());

        return p;
    }

    public static Product toEntity(
            ProductRequestDTO dto,
            Category category,
            Supplier supplier,
            Unit unit
    ) {
        Product p = new Product();

        p.setId(dto.getId()); // null = create, có = update
        p.setSku(dto.getSku());
        p.setProductName(dto.getProductName());

        p.setCategory(category);
        p.setSupplier(supplier);
        p.setUnit(unit);

        p.setPrice(dto.getPrice());
        p.setCost(dto.getCost());
        p.setDescription(dto.getDescription());
        p.setImageUrl(dto.getImageUrl());
        p.setActive(dto.isActive());

        return p;
    }

    public static ProductResponseDTO toDTO(Product entity) {
        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(entity.getId());
        dto.setSku(entity.getSku());
        dto.setProductName(entity.getProductName());

        dto.setCategoryId(entity.getCategory().getId());
        dto.setCategoryName(entity.getCategory().getName());

        dto.setSupplierId(entity.getSupplier().getId());
        dto.setSupplierName(entity.getSupplier().getName());

        dto.setUnitId(entity.getUnit().getId());
        dto.setUnitName(entity.getUnit().getName());

        dto.setPrice(entity.getPrice());
        dto.setCost(entity.getCost());
        dto.setDescription(entity.getDescription());
        dto.setImageUrl(entity.getImageUrl());
        dto.setActive(entity.isActive());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
