package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.PermissionRequestDTO;
import com.conveniencestore.DTO.PermissionResponseDTO;
import com.conveniencestore.entity.Permission;

public class PermissionMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static PermissionResponseDTO toResponseDTO(Permission permission) {
        if (permission == null) {
            return null;
        }

        PermissionResponseDTO dto = new PermissionResponseDTO();
        dto.setId(permission.getId());
        dto.setName(permission.getName());
        dto.setDescription(permission.getDescription());
        dto.setPermissionAction(permission.getPermissionAction());

        // Map group using PermissionGroupMapper
        if (permission.getGroup() != null) {
            dto.setGroup(PermissionGroupMapper.toResponseDTO(permission.getGroup()));
        }

        return dto;
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<PermissionResponseDTO> toResponseDTOList(List<Permission> permissions) {
        if (permissions == null) {
            return null;
        }

        return permissions.stream()
                .map(PermissionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static Permission toEntity(PermissionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Permission permission = new Permission();
        permission.setName(dto.getName());
        permission.setDescription(dto.getDescription());
        permission.setPermissionAction(dto.getPermissionAction());

        return permission;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(PermissionRequestDTO dto, Permission permission) {
        if (dto == null || permission == null) {
            return;
        }

        if (dto.getName() != null && !dto.getName().isBlank()) {
            permission.setName(dto.getName());
        }

        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            permission.setDescription(dto.getDescription());
        }

        if (dto.getPermissionAction() != null) {
            permission.setPermissionAction(dto.getPermissionAction());
        }
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static Permission toEntity(PermissionResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        permission.setDescription(dto.getDescription());
        permission.setPermissionAction(dto.getPermissionAction());

        // Map group using PermissionGroupMapper (convert ResponseDTO back to entity)
        if (dto.getGroup() != null) {
            permission.setGroup(PermissionGroupMapper.toEntity(dto.getGroup()));
        }

        return permission;
    }
}
