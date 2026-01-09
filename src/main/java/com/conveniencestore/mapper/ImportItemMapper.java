package com.conveniencestore.mapper;

import com.conveniencestore.DTO.ImportItemRequestDTO;
import com.conveniencestore.DTO.ImportItemResponseDTO;
import com.conveniencestore.entity.Import;
import com.conveniencestore.entity.ImportItem;
import com.conveniencestore.entity.Product;

public class ImportItemMapper {

   
    public static ImportItem toEntityVer2(ImportItemRequestDTO dto) {
        ImportItem item = new ImportItem();

        item.setId(dto.getId());
        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());
        item.setTotalPrice(dto.getTotalPrice());

        return item;
    }

    
    public static ImportItem toEntity(
            ImportItemRequestDTO dto,
            Import importRecord,
            Product product
    ) {
        ImportItem item = new ImportItem();

        item.setId(dto.getId());
        item.setImportRecord(importRecord);
        item.setProduct(product);

        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());
        item.setTotalPrice(dto.getTotalPrice());

        return item;
    }

    
    public static ImportItemResponseDTO toDTO(ImportItem entity) {
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
            dto.setProductName(entity.getProduct().getProductName());
        }

        return dto;
    }
}
