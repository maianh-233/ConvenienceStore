package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryRequestDTO {

    private Long id;          // null = create, có = update
    private Long productId;   // dùng khi map phức tạp
    private int quantity;
    private LocalDateTime lastCheckedAt;
}
