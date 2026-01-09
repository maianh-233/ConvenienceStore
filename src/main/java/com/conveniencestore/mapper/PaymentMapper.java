package com.conveniencestore.mapper;

import com.conveniencestore.DTO.PaymentRequestDTO;
import com.conveniencestore.DTO.PaymentResponseDTO;
import com.conveniencestore.entity.Order;
import com.conveniencestore.entity.Payment;

public class PaymentMapper {

   
    public static Payment toEntityVer2(PaymentRequestDTO dto) {
        Payment p = new Payment();

        p.setId(dto.getId());
        p.setAmount(dto.getAmount());
        p.setMethod(dto.getMethod());
        p.setTransactionRef(dto.getTransactionRef());
        p.setStatus(dto.getStatus());

        return p;
    }

    public static Payment toEntity(PaymentRequestDTO dto, Order order) {
        Payment p = new Payment();

        p.setId(dto.getId());
        p.setOrder(order);
        p.setAmount(dto.getAmount());
        p.setMethod(dto.getMethod());
        p.setTransactionRef(dto.getTransactionRef());
        p.setStatus(dto.getStatus());

        return p;
    }

    public static PaymentResponseDTO toDTO(Payment entity) {
        PaymentResponseDTO dto = new PaymentResponseDTO();

        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setMethod(entity.getMethod());
        dto.setTransactionRef(entity.getTransactionRef());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getOrder() != null) {
            dto.setOrderId(entity.getOrder().getId());
        }

        return dto;
    }
}
