package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SupplierResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String note;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int isDeleted;
}