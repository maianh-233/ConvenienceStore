package com.conveniencestore.mapper;

import com.conveniencestore.DTO.CustomerRequestDTO;
import com.conveniencestore.DTO.CustomerResponseDTO;
import com.conveniencestore.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDTO dto) {
        Customer c = new Customer();

        c.setId(dto.getId()); // null = create, có = update
        c.setFullName(dto.getFullName());
        c.setDateOfBirth(dto.getDateOfBirth());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        c.setAddress(dto.getAddress());
        c.setIdentityNumber(dto.getIdentityNumber());
        c.setGender(dto.getGender());

        c.setTier(dto.getTier());
        c.setPoints(dto.getPoints());
        c.setIsDeleted(dto.getIsDeleted() != 0);

        // password hash xử lý ở Service
        c.setPassword(dto.getPassword());

        return c;
    }

    public static CustomerResponseDTO toDTO(Customer entity) {
        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setIdentityNumber(entity.getIdentityNumber());
        dto.setGender(entity.getGender());
        dto.setIsDeleted(entity.isDeleted() ? 1 : 0);

        dto.setTier(entity.getTier());
        dto.setPoints(entity.getPoints());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
