package com.conveniencestore.mapper;

import com.conveniencestore.DTO.OrderRequestDTO;
import com.conveniencestore.DTO.OrderResponseDTO;
import com.conveniencestore.entity.Customer;
import com.conveniencestore.entity.Order;
import com.conveniencestore.entity.Payment;
import com.conveniencestore.entity.Promotion;
import com.conveniencestore.entity.User;

public class OrderMapper {

    /* ================= REQUEST DTO -> ENTITY ================= */
    public static Order toEntity(OrderRequestDTO dto) {
        if (dto == null) return null;

        Order order = new Order();

        order.setId(dto.getId());
        order.setOrderNumber(dto.getOrderNumber());
        order.setStatus(dto.getStatus());
        order.setSubtotal(dto.getSubtotal());
        order.setDiscount(dto.getDiscount());
        order.setTotalAmount(dto.getTotalAmount());
        order.setNote(dto.getNote());
        order.setShippingAddress(dto.getShippingAddress());
        order.setIsOnline(dto.getIsOnline());
        order.setIsDeleted(dto.getIsDeleted());

        // Customer
        if (dto.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(dto.getCustomerId());
            order.setCustomer(customer);
        }

        // Staff
        if (dto.getStaffId() != null) {
            User staff = new User();
            staff.setId(dto.getStaffId());
            order.setStaff(staff);
        }

        // Promotion
        if (dto.getPromotionId() != null) {
            Promotion promotion = new Promotion();
            promotion.setId(dto.getPromotionId());
            order.setPromotion(promotion);
        }

        return order;
    }

    /* ================= ENTITY -> RESPONSE DTO ================= */
    public static OrderResponseDTO toResponseDTO(Order order) {
        if (order == null) return null;

        OrderResponseDTO dto = new OrderResponseDTO();

        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setStatus(order.getStatus());
        dto.setSubtotal(order.getSubtotal());
        dto.setDiscount(order.getDiscount());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setNote(order.getNote());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setIsOnline(order.getIsOnline());
        dto.setIsDeleted(order.getIsDeleted());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());

        // Customer
        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getId());
            dto.setCustomerName(order.getCustomer().getFullName()); // đổi field nếu khác
        }

        // Staff
        if (order.getStaff() != null) {
            dto.setStaffId(order.getStaff().getId());
            dto.setStaffName(order.getStaff().getFullName());
        }

        // Promotion
        if (order.getPromotion() != null) {
            dto.setPromotionId(order.getPromotion().getId());
            dto.setPromotionCode(order.getPromotion().getCode());
        }

        // Payment
        Payment payment = order.getPayment();
        if (payment != null) {
            dto.setTransactionref(payment.getTransactionRef());
            dto.setPaymentMethod(payment.getMethod());
            dto.setPaymentStatus(payment.getStatus());
        }

        return dto;
    }
}
