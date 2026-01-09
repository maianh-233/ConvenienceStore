package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PromotionRequestDTO {

    private Long id;                 // null = create, có = update
    private String name;
    private String type;             // percent / amount
    private BigDecimal value;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private int isActive;
    private int isDeleted;

    private String customerTier;
    private Integer maxUses;
    private BigDecimal minOrderAmount;

    private String note;
    private String applicableTiers;  // "REGULAR,VIP"
}
