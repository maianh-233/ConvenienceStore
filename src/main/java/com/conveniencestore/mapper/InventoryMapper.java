package com.conveniencestore.mapper;

import com.conveniencestore.DTO.InventoryRequestDTO;
import com.conveniencestore.DTO.InventoryResponseDTO;
import com.conveniencestore.entity.Inventory;
import com.conveniencestore.entity.Product;

public class InventoryMapper {


    public static Inventory toEntityVer2(InventoryRequestDTO dto) {
        Inventory i = new Inventory();
        i.setId(dto.getId());
        i.setQuantity(dto.getQuantity());
   
        return i;
    }

    
    public static Inventory toEntity(
            InventoryRequestDTO dto,
            Product product
    ) {
        Inventory i = new Inventory();

        i.setId(dto.getId());
        i.setProduct(product);
        i.setQuantity(dto.getQuantity());
    

        return i;
    }

    
    public static InventoryResponseDTO toDTO(Inventory entity) {
        InventoryResponseDTO dto = new InventoryResponseDTO();

        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
    
        dto.setUpdatedAt(entity.getUpdatedAt());

        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getProductName());
        }

        return dto;
    }
}
