package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.PermissionGroupRequestDTO;
import com.conveniencestore.DTO.PermissionGroupResponseDTO;
import com.conveniencestore.entity.PermissionGroup;

public class PermissionGroupMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static PermissionGroupResponseDTO toResponseDTO(PermissionGroup permissionGroup) {
        if (permissionGroup == null) {
            return null;
        }

        return new PermissionGroupResponseDTO(
            permissionGroup.getId(),
            permissionGroup.getCode(),
            permissionGroup.getName()
        );
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<PermissionGroupResponseDTO> toResponseDTOList(List<PermissionGroup> permissionGroups) {
        if (permissionGroups == null) {
            return null;
        }

        return permissionGroups.stream()
                .map(PermissionGroupMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static PermissionGroup toEntity(PermissionGroupRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setCode(dto.getCode());
        permissionGroup.setName(dto.getName());

        return permissionGroup;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(PermissionGroupRequestDTO dto, PermissionGroup permissionGroup) {
        if (dto == null || permissionGroup == null) {
            return;
        }

        if (dto.getCode() != null && !dto.getCode().isBlank()) {
            permissionGroup.setCode(dto.getCode());
        }

        if (dto.getName() != null && !dto.getName().isBlank()) {
            permissionGroup.setName(dto.getName());
        }
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static PermissionGroup toEntity(PermissionGroupResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setId(dto.getId());
        permissionGroup.setCode(dto.getCode());
        permissionGroup.setName(dto.getName());

        return permissionGroup;
    }
}
