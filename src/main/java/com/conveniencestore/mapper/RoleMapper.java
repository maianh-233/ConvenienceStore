package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.RoleRequestDTO;
import com.conveniencestore.DTO.RoleResponseDTO;
import com.conveniencestore.entity.Role;

public class RoleMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static RoleResponseDTO toResponseDTO(Role role) {
        if (role == null) {
            return null;
        }

        return new RoleResponseDTO(
            role.getId(),
            role.getName(),
            role.getIsActive()
        );
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<RoleResponseDTO> toResponseDTOList(List<Role> roles) {
        if (roles == null) {
            return null;
        }

        return roles.stream()
                .map(RoleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static Role toEntity(RoleRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Role role = new Role();
        role.setName(dto.getName());

        return role;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(RoleRequestDTO dto, Role role) {
        if (dto == null || role == null) {
            return;
        }

        if (dto.getName() != null && !dto.getName().isBlank()) {
            role.setName(dto.getName());
        }
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static Role toEntity(RoleResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());

        return role;
    }
}
