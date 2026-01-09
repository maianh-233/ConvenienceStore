package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryResponseDTO {

    private Long id;

    private Long productId;
    private String productName;

    private int quantity;
    private LocalDateTime lastCheckedAt;
    private LocalDateTime updatedAt;
}
