package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PromotionResponseDTO {

    private Long id;
    private String code;
    private String name;
    private String type;
    private BigDecimal value;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private int isActive;


    private String customerTier;
    private Integer maxUses;
    private BigDecimal minOrderAmount;

    private String note;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
