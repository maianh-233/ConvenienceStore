package com.conveniencestore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.conveniencestore.DTO.PaymentRequestDTO;
import com.conveniencestore.DTO.PaymentResponseDTO;
import com.conveniencestore.entity.Payment;

public class PaymentMapper {

    /* ================= ENTITY TO RESPONSE DTO ================= */
    public static PaymentResponseDTO toResponseDTO(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setMethod(payment.getMethod());
        dto.setTransactionRef(payment.getTransactionRef());
        dto.setStatus(payment.getStatus());
        dto.setCreatedAt(payment.getCreatedAt());

        // Extract orderId from relationship
        if (payment.getOrder() != null) {
            dto.setOrderId(payment.getOrder().getId());
        }

        return dto;
    }

    /* ================= LIST OF ENTITIES TO LIST OF RESPONSE DTOS ================= */
    public static List<PaymentResponseDTO> toResponseDTOList(List<Payment> payments) {
        if (payments == null) {
            return null;
        }

        return payments.stream()
                .map(PaymentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* ================= REQUEST DTO TO ENTITY (CREATE) ================= */
    public static Payment toEntity(PaymentRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getMethod());
        payment.setTransactionRef(dto.getTransactionRef());
        payment.setStatus(dto.getStatus());

        return payment;
    }

    /* ================= UPDATE ENTITY FROM REQUEST DTO ================= */
    public static void updateEntityFromDTO(PaymentRequestDTO dto, Payment payment) {
        if (dto == null || payment == null) {
            return;
        }

        if (dto.getAmount() != null) {
            payment.setAmount(dto.getAmount());
        }

        if (dto.getMethod() != null) {
            payment.setMethod(dto.getMethod());
        }

        if (dto.getTransactionRef() != null && !dto.getTransactionRef().isBlank()) {
            payment.setTransactionRef(dto.getTransactionRef());
        }

        if (dto.getStatus() != null) {
            payment.setStatus(dto.getStatus());
        }
    }

    /* ================= RESPONSE DTO TO ENTITY ================= */
    public static Payment toEntity(PaymentResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getMethod());
        payment.setTransactionRef(dto.getTransactionRef());
        payment.setStatus(dto.getStatus());
        payment.setCreatedAt(dto.getCreatedAt());

        return payment;
    }
}
