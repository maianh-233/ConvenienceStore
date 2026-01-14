package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryRequestDTO {

    private Long id;         
    private Long productId;   
    private int quantity;
    private LocalDateTime lastCheckedAt;
}
