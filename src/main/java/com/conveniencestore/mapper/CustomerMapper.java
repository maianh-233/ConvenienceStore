package com.conveniencestore.mapper;

import com.conveniencestore.DTO.CustomerRequestDTO;
import com.conveniencestore.DTO.CustomerResponseDTO;
import com.conveniencestore.entity.Customer;

public class CustomerMapper {

    /* ================== REQUEST DTO -> ENTITY ================== */
    public static Customer toEntity(CustomerRequestDTO dto) {
        Customer customer = new Customer();

        customer.setId(dto.getId()); // dùng cho update
        customer.setFullName(dto.getFullName());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setGender(dto.getGender());
        customer.setTier(dto.getTier());
        customer.setPoints(dto.getPoints());
        customer.setIsDeleted(dto.getIsDeleted());

        return customer;
    }

    /* ================== ENTITY -> RESPONSE DTO ================== */
    public static CustomerResponseDTO toResponseDTO(Customer entity) {
        CustomerResponseDTO dto = new CustomerResponseDTO();

        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setGender(entity.getGender());
        dto.setTier(entity.getTier());
        dto.setPoints(entity.getPoints());
        dto.setIsDeleted(entity.getIsDeleted());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
