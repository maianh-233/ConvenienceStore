package com.conveniencestore.mapper;

import java.math.BigDecimal;

import com.conveniencestore.DTO.ImportItemRequestDTO;
import com.conveniencestore.DTO.ImportItemResponseDTO;
import com.conveniencestore.entity.Import;
import com.conveniencestore.entity.ImportItem;
import com.conveniencestore.entity.Product;

public class ImportItemMapper {

    /* ================== REQUEST DTO -> ENTITY ================== */
    public static ImportItem toEntity(
            ImportItemRequestDTO dto,
            Import importRecord,
            Product product
    ) {
        ImportItem item = new ImportItem();

        item.setId(dto.getId()); // dùng cho update
        item.setImportRecord(importRecord);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());

        // TỰ TÍNH TOTAL PRICE (an toàn)
        BigDecimal total = dto.getUnitPrice()
                .multiply(BigDecimal.valueOf(dto.getQuantity()));
        item.setTotalPrice(total);

        return item;
    }

    /* ================== ENTITY -> RESPONSE DTO ================== */
    public static ImportItemResponseDTO toResponseDTO(
            ImportItem entity
    ) {
        ImportItemResponseDTO dto = new ImportItemResponseDTO();

        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setTotalPrice(entity.getTotalPrice());

        if (entity.getImportRecord() != null) {
            dto.setImportId(entity.getImportRecord().getId());
        }

        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName());
        }

        return dto;
    }
}
