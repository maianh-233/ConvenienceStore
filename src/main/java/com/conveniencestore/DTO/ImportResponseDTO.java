package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ImportResponseDTO {

    private Long id;
    private String importNumber;

    private Long supplierId;
    private String supplierName;

    private Long staffId;
    private String staffName;

    private BigDecimal totalAmount;
    private String note;

    private int isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
