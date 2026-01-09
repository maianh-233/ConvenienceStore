package com.conveniencestore.mapper;

import com.conveniencestore.DTO.ImportRequestDTO;
import com.conveniencestore.DTO.ImportResponseDTO;
import com.conveniencestore.entity.Import;
import com.conveniencestore.entity.Supplier;
import com.conveniencestore.entity.User;

public class ImportMapper {
    public static Import toEntityVer2(ImportRequestDTO dto) {
        Import i = new Import();

        i.setId(dto.getId()); // null = create, có = update
        i.setImportNumber(dto.getImportNumber());
        i.setTotalAmount(dto.getTotalAmount());
        i.setNote(dto.getNote());
        i.setIsDeleted(dto.getIsDeleted());

        return i;
    }

    public static Import toEntity(
            ImportRequestDTO dto,
            Supplier supplier,
            User staff
    ) {
        Import i = new Import();

        i.setId(dto.getId()); // null = create, có = update
        i.setImportNumber(dto.getImportNumber());

        i.setSupplier(supplier);
        i.setStaff(staff);

        i.setTotalAmount(dto.getTotalAmount());
        i.setNote(dto.getNote());
        i.setIsDeleted(dto.getIsDeleted());

        return i;
    }

    public static ImportResponseDTO toDTO(Import entity) {
        ImportResponseDTO dto = new ImportResponseDTO();

        dto.setId(entity.getId());
        dto.setImportNumber(entity.getImportNumber());

        dto.setSupplierId(entity.getSupplier().getId());
        dto.setSupplierName(entity.getSupplier().getName());

        dto.setStaffId(entity.getStaff().getId());
        dto.setStaffName(entity.getStaff().getFullName());

        dto.setTotalAmount(entity.getTotalAmount());
        dto.setNote(entity.getNote());
        dto.setIsDeleted(entity.getIsDeleted());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
