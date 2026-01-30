package com.conveniencestore.mapper;

import com.conveniencestore.DTO.InventoryRequestDTO;
import com.conveniencestore.DTO.InventoryResponseDTO;
import com.conveniencestore.entity.Inventory;
import com.conveniencestore.entity.Product;

public class InventoryMapper {

    /* ================== REQUEST DTO -> ENTITY ================== */
    public static Inventory toEntity(
            InventoryRequestDTO dto,
            Product product
    ) {
        Inventory inventory = new Inventory();

        inventory.setId(dto.getId()); // dùng cho update
        inventory.setProduct(product);
        inventory.setQuantity(dto.getQuantity());

        // thời điểm kiểm kê (nếu có)
        inventory.setLastCheckedAt(dto.getLastCheckedAt());

        return inventory;
    }

    /* ================== ENTITY -> RESPONSE DTO ================== */
    public static InventoryResponseDTO toResponseDTO(
            Inventory entity
    ) {
        InventoryResponseDTO dto = new InventoryResponseDTO();

        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setLastCheckedAt(entity.getLastCheckedAt());

        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getName());
        }

        return dto;
    }
}
