package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryExportItemResponseDTO {
    private Long id;
    private Long exportId;
    private String exportCode;
    private Long productId;
    private String productCode;
    private String productName;
    private int quantity;
    private String note;
}
