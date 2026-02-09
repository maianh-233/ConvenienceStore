package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.OrderItemRequestDTO;
import com.conveniencestore.DTO.OrderItemResponseDTO;
import com.conveniencestore.entity.OrderItem;

public class OrderItemMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static OrderItemResponseDTO toResponseDTO(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setId(orderItem.getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setUnitPrice(orderItem.getUnitPrice());
        dto.setTotalPrice(orderItem.getTotalPrice());
        dto.setCreatedAt(orderItem.getCreatedAt());

        // Extract orderId from relationship
        if (orderItem.getOrder() != null) {
            dto.setOrderId(orderItem.getOrder().getId());
        }

        // Extract productId and productName from relationship
        if (orderItem.getProduct() != null) {
            dto.setProductId(orderItem.getProduct().getId());
            dto.setProductName(orderItem.getProduct().getProductName());
        }

        return dto;
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<OrderItemResponseDTO> toResponseDTOList(List<OrderItem> orderItems) {
        if (orderItems == null) {
            return null;
        }

        return orderItems.stream()
                .map(OrderItemMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static OrderItem toEntity(OrderItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setUnitPrice(dto.getUnitPrice());
        orderItem.setTotalPrice(dto.getTotalPrice());

        return orderItem;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(OrderItemRequestDTO dto, OrderItem orderItem) {
        if (dto == null || orderItem == null) {
            return;
        }

        if (dto.getQuantity() > 0) {
            orderItem.setQuantity(dto.getQuantity());
        }

        if (dto.getUnitPrice() != null) {
            orderItem.setUnitPrice(dto.getUnitPrice());
        }

        if (dto.getTotalPrice() != null) {
            orderItem.setTotalPrice(dto.getTotalPrice());
        }
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static OrderItem toEntity(OrderItemResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setId(dto.getId());
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setUnitPrice(dto.getUnitPrice());
        orderItem.setTotalPrice(dto.getTotalPrice());
        orderItem.setCreatedAt(dto.getCreatedAt());

        return orderItem;
    }
}
