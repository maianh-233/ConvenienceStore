package com.conveniencestore.mapper;

import com.conveniencestore.DTO.UserRequestDTO;
import com.conveniencestore.DTO.UserResponseDTO;
import com.conveniencestore.entity.Role;
import com.conveniencestore.entity.User;

public class UserMapper {

    public static User toEntityVer2(UserRequestDTO dto) {
        User u = new User();

        u.setId(dto.getId()); // null = create, có = update
        u.setUsername(dto.getUsername());
        u.setFullName(dto.getFullName());
        u.setDateOfBirth(dto.getDateOfBirth());
        u.setEmail(dto.getEmail());
        u.setPhone(dto.getPhone());
        u.setIdentityNumber(dto.getIdentityNumber());
        u.setAddress(dto.getAddress());
        u.setImg_url(dto.getImgUrl());
        u.setGender(dto.getGender());

        u.setActive(dto.getActive());
        u.setLocked(dto.getLocked());

        return u;
    }

    public static User toEntity(UserRequestDTO dto, Role role) {
        User u = new User();

        u.setId(dto.getId());              // null = create, có = update
        u.setUsername(dto.getUsername());
        u.setFullName(dto.getFullName());
        u.setDateOfBirth(dto.getDateOfBirth());
        u.setEmail(dto.getEmail());
        u.setPhone(dto.getPhone());
        u.setIdentityNumber(dto.getIdentityNumber());
        u.setAddress(dto.getAddress());
        u.setImg_url(dto.getImgUrl());
        u.setGender(dto.getGender());

        u.setRole(role);

        u.setActive(dto.getActive());
        u.setLocked(dto.getLocked());

        return u;
    }

    public static UserResponseDTO toDTO(User entity) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setFullName(entity.getFullName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setIdentityNumber(entity.getIdentityNumber());
        dto.setAddress(entity.getAddress());
        dto.setImgUrl(entity.getImg_url());
        dto.setGender(entity.getGender());

        dto.setRoleId(entity.getRole().getId());
        dto.setRoleName(entity.getRole().getName());

        dto.setActive(entity.getActive());
        dto.setLocked(entity.getLocked());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setLastLogin(entity.getLastLogin());

        return dto;
    }
}
