package com.conveniencestore.mapper;

import com.conveniencestore.DTO.InventoryExportRequestDTO;
import com.conveniencestore.DTO.InventoryExportResponseDTO;
import com.conveniencestore.entity.InventoryExport;
import com.conveniencestore.entity.User;

public class InventoryExportMapper {

    /* ================== REQUEST DTO -> ENTITY ================== */
    public static InventoryExport toEntity(
            InventoryExportRequestDTO dto,
            User createdBy
    ) {
        InventoryExport export = new InventoryExport();

        export.setId(dto.getId()); // dùng cho update
        export.setCode(dto.getCode());
        export.setCreatedBy(createdBy);
        export.setType(dto.getType());
        export.setStatus(dto.getStatus());
        export.setNote(dto.getNote());
        export.setIsDeleted(dto.getIsDeleted());

        return export;
    }

    /* ================== ENTITY -> RESPONSE DTO ================== */
    public static InventoryExportResponseDTO toResponseDTO(
            InventoryExport entity
    ) {
        InventoryExportResponseDTO dto = new InventoryExportResponseDTO();

        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        dto.setNote(entity.getNote());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        if (entity.getCreatedBy() != null) {
            dto.setCreatedById(entity.getCreatedBy().getId());
            dto.setCreatedByName(entity.getCreatedBy().getFullName());
        }

        return dto;
    }
}
