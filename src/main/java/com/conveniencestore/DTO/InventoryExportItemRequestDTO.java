package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryExportItemRequestDTO {
    private Long id;         
    private Long exportId;    
    private Long productId;  
    private int quantity;   
    private String note;
}
