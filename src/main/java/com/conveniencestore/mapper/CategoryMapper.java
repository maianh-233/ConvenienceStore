package com.conveniencestore.mapper;

import com.conveniencestore.DTO.CategoryRequestDTO;
import com.conveniencestore.DTO.CategoryResponseDTO;
import com.conveniencestore.entity.Category;

public class CategoryMapper {

    /* ================== REQUEST DTO -> ENTITY ================== */
    public static Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();

        category.setId(dto.getId()); // dùng cho update
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setIsDeleted(dto.getIsDeleted());

        return category;
    }

    /* ================== ENTITY -> RESPONSE DTO ================== */
    public static CategoryResponseDTO toResponseDTO(Category entity) {
        CategoryResponseDTO dto = new CategoryResponseDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setIsDeleted(entity.getIsDeleted());

        return dto;
    }
}
