package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import com.conveniencestore.constant.OrderStatus;

@Getter
@Setter
public class OrderRequestDTO {

    private Long id;
    private String orderNumber;
    private Long customerId;
    private Long staffId;
    private Long promotionId;
    private OrderStatus status;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal totalAmount;
    private String note;
    private String shippingAddress;
    private int isOnline;
    private int isDeleted;
}
