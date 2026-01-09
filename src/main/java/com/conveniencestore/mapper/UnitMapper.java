package com.conveniencestore.mapper;

import com.conveniencestore.DTO.CategoryRequestDTO;
import com.conveniencestore.DTO.CategoryResponseDTO;
import com.conveniencestore.DTO.UnitRequestDTO;
import com.conveniencestore.DTO.UnitResponseDTO;

import com.conveniencestore.entity.Unit;

public class UnitMapper {
        public static Unit toEntity(UnitRequestDTO dto) {
        Unit u = new Unit();
        // id CHỈ dùng cho UPDATE (Hibernate sẽ ignore nếu null)
        u.setId(dto.getId());
        u.setName(dto.getName());
        u.setDescription(dto.getDescription());
        return u;
    }

    public static UnitResponseDTO toDTO(Unit entity) {
        UnitResponseDTO dto = new UnitResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setIsDeleted(entity.getIsDeleted());
        return dto;
    }
}
