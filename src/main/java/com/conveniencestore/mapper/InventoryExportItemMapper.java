package com.conveniencestore.mapper;

import com.conveniencestore.DTO.InventoryExportItemRequestDTO;
import com.conveniencestore.DTO.InventoryExportItemResponseDTO;
import com.conveniencestore.entity.InventoryExport;
import com.conveniencestore.entity.InventoryExportItem;
import com.conveniencestore.entity.Product;

public class InventoryExportItemMapper {

    /* ================== REQUEST DTO -> ENTITY ================== */
    public static InventoryExportItem toEntity(
            InventoryExportItemRequestDTO dto,
            InventoryExport export,
            Product product
    ) {
        InventoryExportItem item = new InventoryExportItem();

        item.setId(dto.getId()); // dùng cho update
        item.setExport(export);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setNote(dto.getNote());

        return item;
    }

    /* ================== ENTITY -> RESPONSE DTO ================== */
    public static InventoryExportItemResponseDTO toResponseDTO(
            InventoryExportItem entity
    ) {
        InventoryExportItemResponseDTO dto = new InventoryExportItemResponseDTO();

        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setNote(entity.getNote());

        // Export
        if (entity.getExport() != null) {
            dto.setExportId(entity.getExport().getId());
            dto.setExportCode(entity.getExport().getCode());
        }

        // Product
        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductCode(entity.getProduct().getCode());
            dto.setProductName(entity.getProduct().getName());
        }

        return dto;
    }
}
