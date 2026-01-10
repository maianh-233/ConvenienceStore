package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.conveniencestore.constant.OrderStatus;

@Getter
@Setter
public class OrderResponseDTO {

    private Long id;
   
    private String orderNumber;

    private Long customerId;
    private String customerName;

    private Long staffId;
    private String staffName;

    private Long promotionId;
    private String promotionCode;

    private OrderStatus status;

    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal totalAmount;

    private String note;
    private String shippingAddress;

    private int isOnline;
    private int isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
