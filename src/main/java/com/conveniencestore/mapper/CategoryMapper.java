package com.conveniencestore.mapper;
import com.conveniencestore.entity.Category;
import com.conveniencestore.DTO.CategoryRequestDTO;
import com.conveniencestore.DTO.CategoryResponseDTO;
public class CategoryMapper {

    public static Category toEntity(CategoryRequestDTO dto) {
        Category c = new Category();

        // id CHỈ dùng cho UPDATE (Hibernate sẽ ignore nếu null)
        s.setId(dto.getId());
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        return c;
    }

    public static CategoryResponseDTO toDTO(Category entity) {
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