package com.conveniencestore.mapper;

import com.conveniencestore.DTO.PromotionRequestDTO;
import com.conveniencestore.DTO.PromotionResponseDTO;
import com.conveniencestore.entity.Promotion;

public class PromotionMapper {

    public static Promotion toEntity(PromotionRequestDTO dto) {
        Promotion p = new Promotion();

        p.setId(dto.getId()); // null = create, có = update
        p.setCode(dto.getCode());
        p.setName(dto.getName());
        p.setType(dto.getType());
        p.setValue(dto.getValue());
        p.setStartAt(dto.getStartAt());
        p.setEndAt(dto.getEndAt());
        p.setIsActive(dto.getIsActive());
        p.setCustomerTier(dto.getCustomerTier());
        p.setMaxUses(dto.getMaxUses());
        p.setMinOrderAmount(dto.getMinOrderAmount());
        p.setNote(dto.getNote());
  

        return p;
    }

    public static PromotionResponseDTO toDTO(Promotion entity) {
        PromotionResponseDTO dto = new PromotionResponseDTO();

        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setValue(entity.getValue());
        dto.setStartAt(entity.getStartAt());
        dto.setEndAt(entity.getEndAt());
        dto.setIsActive(entity.getIsActive());
        dto.setCustomerTier(entity.getCustomerTier());
        dto.setMaxUses(entity.getMaxUses());
        dto.setMinOrderAmount(entity.getMinOrderAmount());
        dto.setNote(entity.getNote());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
