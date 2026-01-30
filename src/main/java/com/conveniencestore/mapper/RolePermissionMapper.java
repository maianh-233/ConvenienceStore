package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.RolePermissionIdDTO;
import com.conveniencestore.DTO.RolePermissionRequestDTO;
import com.conveniencestore.DTO.RolePermissionResponseDTO;
import com.conveniencestore.entity.RolePermission;
import com.conveniencestore.entity.RolePermissionId;

public class RolePermissionMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static RolePermissionResponseDTO toResponseDTO(RolePermission rolePermission) {
        if (rolePermission == null) {
            return null;
        }

        RolePermissionResponseDTO dto = new RolePermissionResponseDTO();

        // Map composite key ID
        if (rolePermission.getId() != null) {
            dto.setId(new RolePermissionIdDTO(
                rolePermission.getId().getRoleId(),
                rolePermission.getId().getPermissionId()
            ));
        }

        // Map role using RoleMapper
        if (rolePermission.getRole() != null) {
            dto.setRole(RoleMapper.toResponseDTO(rolePermission.getRole()));
        }

        // Map permission using PermissionMapper
        if (rolePermission.getPermission() != null) {
            dto.setPermission(PermissionMapper.toResponseDTO(rolePermission.getPermission()));
        }

        dto.setIsActive(rolePermission.getIsActive());

        return dto;
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<RolePermissionResponseDTO> toResponseDTOList(List<RolePermission> rolePermissions) {
        if (rolePermissions == null) {
            return null;
        }

        return rolePermissions.stream()
                .map(RolePermissionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static RolePermission toEntity(RolePermissionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        RolePermission rolePermission = new RolePermission();

        // Map composite key ID
        if (dto.getId() != null) {
            rolePermission.setId(new RolePermissionId(
                dto.getId().getRoleId(),
                dto.getId().getPermissionId()
            ));
        }

        rolePermission.setIsActive(dto.getIsActive());

        return rolePermission;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(RolePermissionRequestDTO dto, RolePermission rolePermission) {
        if (dto == null || rolePermission == null) {
            return;
        }

        if (dto.getIsActive() >= 0) {
            rolePermission.setIsActive(dto.getIsActive());
        }
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static RolePermission toEntity(RolePermissionResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        RolePermission rolePermission = new RolePermission();

        // Map composite key ID
        if (dto.getId() != null) {
            rolePermission.setId(new RolePermissionId(
                dto.getId().getRoleId(),
                dto.getId().getPermissionId()
            ));
        }

        // Map role using RoleMapper (convert ResponseDTO back to entity)
        if (dto.getRole() != null) {
            rolePermission.setRole(RoleMapper.toEntity(dto.getRole()));
        }

        // Map permission using PermissionMapper (convert ResponseDTO back to entity)
        if (dto.getPermission() != null) {
            rolePermission.setPermission(PermissionMapper.toEntity(dto.getPermission()));
        }

        rolePermission.setIsActive(dto.getIsActive());

        return rolePermission;
    }
}
