package com.conveniencestore.mapper;

import com.conveniencestore.DTO.SupplierRequestDTO;
import com.conveniencestore.DTO.SupplierResponseDTO;
import com.conveniencestore.entity.Supplier;

public class SupplierMapper {

    public static Supplier toEntity(SupplierRequestDTO dto) {
        Supplier s = new Supplier();

        // id CHỈ dùng cho UPDATE (Hibernate sẽ ignore nếu null)
        s.setId(dto.getId());

        s.setName(dto.getName());
        s.setEmail(dto.getEmail());
        s.setPhone(dto.getPhone());
        s.setAddress(dto.getAddress());
        s.setNote(dto.getNote());

        return s;
    }

    public static SupplierResponseDTO toDTO(Supplier entity) {
        SupplierResponseDTO dto = new SupplierResponseDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setNote(entity.getNote());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setIsDeleted(entity.getIsDeleted());

        return dto;
    }
}
