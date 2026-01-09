package com.conveniencestore.mapper;

import com.conveniencestore.DTO.OrderRequestDTO;
import com.conveniencestore.DTO.OrderResponseDTO;
import com.conveniencestore.entity.*;

public class OrderMapper {

    public static Order toEntityVer2(OrderRequestDTO dto) {
        Order o = new Order();

        o.setId(dto.getId()); // null = create, có = update
        o.setOrderNumber(dto.getOrderNumber());

        o.setStatus(dto.getStatus());
        o.setSubtotal(dto.getSubtotal());
        o.setDiscount(dto.getDiscount());
        o.setTotalAmount(dto.getTotalAmount());

        o.setNote(dto.getNote());
        o.setShippingAddress(dto.getShippingAddress());

        o.setIsOnline(dto.getIsOnline());
        o.setIsDeleted(dto.getIsDeleted());

        return o;
    }

    public static Order toEntity(
            OrderRequestDTO dto,
            Customer customer,
            User staff,
            Promotion promotion
    ) {
        Order o = new Order();

        o.setId(dto.getId()); // null = create, có = update
        o.setOrderNumber(dto.getOrderNumber());

        o.setCustomer(customer);
        o.setStaff(staff);
        o.setPromotion(promotion);

        o.setStatus(dto.getStatus());
        o.setSubtotal(dto.getSubtotal());
        o.setDiscount(dto.getDiscount());
        o.setTotalAmount(dto.getTotalAmount());

        o.setNote(dto.getNote());
        o.setShippingAddress(dto.getShippingAddress());

        o.setIsOnline(dto.getIsOnline());
        o.setIsDeleted(dto.getIsDeleted());

        return o;
    }

    public static OrderResponseDTO toDTO(Order entity) {
        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());

        dto.setCustomerId(
                entity.getCustomer() != null ? entity.getCustomer().getId() : null
        );
        dto.setCustomerName(
                entity.getCustomer() != null ? entity.getCustomer().getFullName() : null
        );

        dto.setStaffId(
                entity.getStaff() != null ? entity.getStaff().getId() : null
        );
        dto.setStaffName(
                entity.getStaff() != null ? entity.getStaff().getFullName() : null
        );

        dto.setPromotionId(
                entity.getPromotion() != null ? entity.getPromotion().getId() : null
        );
        dto.setPromotionCode(
                entity.getPromotion() != null ? entity.getPromotion().getCode() : null
        );

        dto.setStatus(entity.getStatus());
        dto.setSubtotal(entity.getSubtotal());
        dto.setDiscount(entity.getDiscount());
        dto.setTotalAmount(entity.getTotalAmount());

        dto.setNote(entity.getNote());
        dto.setShippingAddress(entity.getShippingAddress());

        dto.setIsOnline(entity.getIsOnline());
        dto.setIsDeleted(entity.getIsDeleted());

        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
