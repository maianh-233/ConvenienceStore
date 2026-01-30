package com.conveniencestore.mapper;

import com.conveniencestore.DTO.ImportRequestDTO;
import com.conveniencestore.DTO.ImportResponseDTO;
import com.conveniencestore.entity.Import;
import com.conveniencestore.entity.Supplier;
import com.conveniencestore.entity.User;

public class ImportMapper {

    /* ================== REQUEST DTO -> ENTITY ================== */
    public static Import toEntity(
            ImportRequestDTO dto,
            Supplier supplier,
            User staff
    ) {
        Import importRecord = new Import();

        importRecord.setId(dto.getId()); // dùng cho update
        importRecord.setImportNumber(dto.getImportNumber());
        importRecord.setSupplier(supplier);
        importRecord.setStaff(staff);
        importRecord.setNote(dto.getNote());
        importRecord.setIsDeleted(dto.getIsDeleted());

        // KHÔNG set status ở đây (để @PrePersist xử lý)
        // KHÔNG set totalAmount (tính từ ImportItem)

        return importRecord;
    }

    /* ================== ENTITY -> RESPONSE DTO ================== */
    public static ImportResponseDTO toResponseDTO(
            Import entity
    ) {
        ImportResponseDTO dto = new ImportResponseDTO();

        dto.setId(entity.getId());
        dto.setImportNumber(entity.getImportNumber());
        dto.setStatus(entity.getStatus());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setNote(entity.getNote());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        if (entity.getSupplier() != null) {
            dto.setSupplierId(entity.getSupplier().getId());
            dto.setSupplierName(entity.getSupplier().getName());
        }

        if (entity.getStaff() != null) {
            dto.setStaffId(entity.getStaff().getId());
            dto.setStaffName(entity.getStaff().getFullName());
        }

        return dto;
    }
}
