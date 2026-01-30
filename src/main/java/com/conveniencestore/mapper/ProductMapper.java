package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.ProductRequestDTO;
import com.conveniencestore.DTO.ProductResponseDTO;
import com.conveniencestore.entity.Product;

public class ProductMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static ProductResponseDTO toResponseDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setSku(product.getSku());
        dto.setBarcode(product.getBarcode());
        dto.setProductName(product.getProductName());
        
        // Set category info
        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }
        
        // Set supplier info
        if (product.getSupplier() != null) {
            dto.setSupplierId(product.getSupplier().getId());
            dto.setSupplierName(product.getSupplier().getName());
        }
        
        // Set unit info
        if (product.getUnit() != null) {
            dto.setUnitId(product.getUnit().getId());
            dto.setUnitName(product.getUnit().getName());
        }

        // Set Inventory Infor

        if(product.getInventory() !=null){
            dto.setQuantity(product.getInventory().getQuantity());
            dto.setQuantityupdatedAt(product.getInventory().getUpdatedAt());
        }
        
        dto.setPrice(product.getPrice());
        dto.setCost(product.getCost());
        dto.setDescription(product.getDescription());
        dto.setImageUrl(product.getImageUrl());
        dto.setIsActive(product.getIsActive());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());

        return dto;
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<ProductResponseDTO> toResponseDTOList(List<Product> products) {
        if (products == null) {
            return null;
        }

        return products.stream()
                .map(ProductMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static Product toEntity(ProductRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setSku(dto.getSku());
        product.setBarcode(dto.getBarcode());
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setCost(dto.getCost());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());
        product.setIsActive(dto.getIsActive());

        return product;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(ProductRequestDTO dto, Product product) {
        if (dto == null || product == null) {
            return;
        }

        if (dto.getSku() != null && !dto.getSku().isBlank()) {
            product.setSku(dto.getSku());
        }

        if (dto.getBarcode() != null && !dto.getBarcode().isBlank()) {
            product.setBarcode(dto.getBarcode());
        }

        if (dto.getProductName() != null && !dto.getProductName().isBlank()) {
            product.setProductName(dto.getProductName());
        }

        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }

        if (dto.getCost() != null) {
            product.setCost(dto.getCost());
        }

        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }

        if (dto.getImageUrl() != null) {
            product.setImageUrl(dto.getImageUrl());
        }

        product.setIsActive(dto.getIsActive());
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static Product toEntity(ProductResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setId(dto.getId());
        product.setSku(dto.getSku());
        product.setBarcode(dto.getBarcode());
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        product.setCost(dto.getCost());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());
        product.setIsActive(dto.getIsActive());
        product.setCreatedAt(dto.getCreatedAt());
        product.setUpdatedAt(dto.getUpdatedAt());

        return product;
    }
}
