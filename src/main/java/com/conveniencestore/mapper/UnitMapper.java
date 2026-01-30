package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.UnitRequestDTO;
import com.conveniencestore.DTO.UnitResponseDTO;
import com.conveniencestore.entity.Unit;

public class UnitMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static UnitResponseDTO toResponseDTO(Unit unit) {
        if (unit == null) {
            return null;
        }

        UnitResponseDTO dto = new UnitResponseDTO();
        dto.setId(unit.getId());
        dto.setName(unit.getName());
        dto.setDescription(unit.getDescription());
        dto.setCreatedAt(unit.getCreatedAt());
        dto.setUpdatedAt(unit.getUpdatedAt());
        dto.setIsDeleted(unit.getIsDeleted());

        return dto;
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<UnitResponseDTO> toResponseDTOList(List<Unit> units) {
        if (units == null) {
            return null;
        }

        return units.stream()
                .map(UnitMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static Unit toEntity(UnitRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Unit unit = new Unit();
        unit.setName(dto.getName());
        unit.setDescription(dto.getDescription());
        unit.setIsDeleted(dto.getIsDeleted());

        return unit;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(UnitRequestDTO dto, Unit unit) {
        if (dto == null || unit == null) {
            return;
        }

        if (dto.getName() != null && !dto.getName().isBlank()) {
            unit.setName(dto.getName());
        }

        if (dto.getDescription() != null) {
            unit.setDescription(dto.getDescription());
        }

        unit.setIsDeleted(dto.getIsDeleted());
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static Unit toEntity(UnitResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Unit unit = new Unit();
        unit.setId(dto.getId());
        unit.setName(dto.getName());
        unit.setDescription(dto.getDescription());
        unit.setCreatedAt(dto.getCreatedAt());
        unit.setUpdatedAt(dto.getUpdatedAt());
        unit.setIsDeleted(dto.getIsDeleted());

        return unit;
    }
}
