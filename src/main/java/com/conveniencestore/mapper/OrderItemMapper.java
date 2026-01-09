package com.conveniencestore.mapper;

import com.conveniencestore.DTO.OrderItemRequestDTO;
import com.conveniencestore.DTO.OrderItemResponseDTO;
import com.conveniencestore.entity.Order;
import com.conveniencestore.entity.OrderItem;
import com.conveniencestore.entity.Product;

public class OrderItemMapper {

    
    public static OrderItem toEntityVer2(OrderItemRequestDTO dto) {
        OrderItem oi = new OrderItem();

        oi.setId(dto.getId());
        oi.setQuantity(dto.getQuantity());
        oi.setUnitPrice(dto.getUnitPrice());
        oi.setTotalPrice(dto.getTotalPrice());

        return oi;
    }


    public static OrderItem toEntity(
            OrderItemRequestDTO dto,
            Order order,
            Product product
    ) {
        OrderItem oi = new OrderItem();

        oi.setId(dto.getId());
        oi.setOrder(order);
        oi.setProduct(product);

        oi.setQuantity(dto.getQuantity());
        oi.setUnitPrice(dto.getUnitPrice());
        oi.setTotalPrice(dto.getTotalPrice());

        return oi;
    }

    public static OrderItemResponseDTO toDTO(OrderItem entity) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();

        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setUnitPrice(entity.getUnitPrice());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getOrder() != null) {
            dto.setOrderId(entity.getOrder().getId());
        }

        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
            dto.setProductName(entity.getProduct().getProductName());
        }

        return dto;
    }
}
