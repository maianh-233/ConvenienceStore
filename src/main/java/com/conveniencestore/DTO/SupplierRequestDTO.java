package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequestDTO {
    private Long id;    
    private String name;
    private String email;
    private String phone;
    private String address;
    private String note;
}