package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.SupplierRequestDTO;
import com.conveniencestore.DTO.SupplierResponseDTO;
import com.conveniencestore.entity.Supplier;

public class SupplierMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static SupplierResponseDTO toResponseDTO(Supplier supplier) {
        if (supplier == null) {
            return null;
        }

        SupplierResponseDTO dto = new SupplierResponseDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setEmail(supplier.getEmail());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());
        dto.setNote(supplier.getNote());
        dto.setCreatedAt(supplier.getCreatedAt());
        dto.setUpdatedAt(supplier.getUpdatedAt());
        dto.setIsDeleted(supplier.getIsDeleted());

        return dto;
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<SupplierResponseDTO> toResponseDTOList(List<Supplier> suppliers) {
        if (suppliers == null) {
            return null;
        }

        return suppliers.stream()
                .map(SupplierMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static Supplier toEntity(SupplierRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());
        supplier.setNote(dto.getNote());

        return supplier;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(SupplierRequestDTO dto, Supplier supplier) {
        if (dto == null || supplier == null) {
            return;
        }

        if (dto.getName() != null && !dto.getName().isBlank()) {
            supplier.setName(dto.getName());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            supplier.setEmail(dto.getEmail());
        }

        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            supplier.setPhone(dto.getPhone());
        }

        if (dto.getAddress() != null) {
            supplier.setAddress(dto.getAddress());
        }

        if (dto.getNote() != null) {
            supplier.setNote(dto.getNote());
        }
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static Supplier toEntity(SupplierResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());
        supplier.setNote(dto.getNote());
        supplier.setCreatedAt(dto.getCreatedAt());
        supplier.setUpdatedAt(dto.getUpdatedAt());
        supplier.setIsDeleted(dto.getIsDeleted());

        return supplier;
    }
}
