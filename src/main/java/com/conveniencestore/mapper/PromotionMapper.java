package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.PromotionRequestDTO;
import com.conveniencestore.DTO.PromotionResponseDTO;
import com.conveniencestore.entity.Promotion;

public class PromotionMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static PromotionResponseDTO toResponseDTO(Promotion promotion) {
        if (promotion == null) {
            return null;
        }

        PromotionResponseDTO dto = new PromotionResponseDTO();
        dto.setId(promotion.getId());
        dto.setCode(promotion.getCode());
        dto.setName(promotion.getName());
        dto.setType(promotion.getType());
        dto.setValue(promotion.getValue());
        dto.setStartAt(promotion.getStartAt());
        dto.setEndAt(promotion.getEndAt());
        dto.setIsActive(promotion.getIsActive());
        dto.setCustomerTier(promotion.getCustomerTier());
        dto.setMaxUses(promotion.getMaxUses());
        dto.setMinOrderAmount(promotion.getMinOrderAmount());
        dto.setNote(promotion.getNote());
        dto.setCreatedAt(promotion.getCreatedAt());
        dto.setUpdatedAt(promotion.getUpdatedAt());

        return dto;
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<PromotionResponseDTO> toResponseDTOList(List<Promotion> promotions) {
        if (promotions == null) {
            return null;
        }

        return promotions.stream()
                .map(PromotionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static Promotion toEntity(PromotionRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Promotion promotion = new Promotion();
        promotion.setCode(dto.getCode());
        promotion.setName(dto.getName());
        promotion.setType(dto.getType());
        promotion.setValue(dto.getValue());
        promotion.setStartAt(dto.getStartAt());
        promotion.setEndAt(dto.getEndAt());
        promotion.setIsActive(dto.getIsActive());
        promotion.setCustomerTier(dto.getCustomerTier());
        promotion.setMaxUses(dto.getMaxUses());
        promotion.setMinOrderAmount(dto.getMinOrderAmount());
        promotion.setNote(dto.getNote());

        return promotion;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(PromotionRequestDTO dto, Promotion promotion) {
        if (dto == null || promotion == null) {
            return;
        }

        if (dto.getCode() != null && !dto.getCode().isBlank()) {
            promotion.setCode(dto.getCode());
        }

        if (dto.getName() != null && !dto.getName().isBlank()) {
            promotion.setName(dto.getName());
        }

        if (dto.getType() != null) {
            promotion.setType(dto.getType());
        }

        if (dto.getValue() != null) {
            promotion.setValue(dto.getValue());
        }

        if (dto.getStartAt() != null) {
            promotion.setStartAt(dto.getStartAt());
        }

        if (dto.getEndAt() != null) {
            promotion.setEndAt(dto.getEndAt());
        }

        promotion.setIsActive(dto.getIsActive());

        if (dto.getCustomerTier() != null) {
            promotion.setCustomerTier(dto.getCustomerTier());
        }

        if (dto.getMaxUses() != null) {
            promotion.setMaxUses(dto.getMaxUses());
        }

        if (dto.getMinOrderAmount() != null) {
            promotion.setMinOrderAmount(dto.getMinOrderAmount());
        }

        if (dto.getNote() != null) {
            promotion.setNote(dto.getNote());
        }
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static Promotion toEntity(PromotionResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Promotion promotion = new Promotion();
        promotion.setId(dto.getId());
        promotion.setCode(dto.getCode());
        promotion.setName(dto.getName());
        promotion.setType(dto.getType());
        promotion.setValue(dto.getValue());
        promotion.setStartAt(dto.getStartAt());
        promotion.setEndAt(dto.getEndAt());
        promotion.setIsActive(dto.getIsActive());
        promotion.setCustomerTier(dto.getCustomerTier());
        promotion.setMaxUses(dto.getMaxUses());
        promotion.setMinOrderAmount(dto.getMinOrderAmount());
        promotion.setNote(dto.getNote());
        promotion.setCreatedAt(dto.getCreatedAt());
        promotion.setUpdatedAt(dto.getUpdatedAt());

        return promotion;
    }
}
