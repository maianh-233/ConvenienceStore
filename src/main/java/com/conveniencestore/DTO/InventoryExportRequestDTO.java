package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.conveniencestore.constant.ExportStatus;
import com.conveniencestore.constant.ExportType;

@Getter
@Setter
public class InventoryExportRequestDTO {
    private Long id;          
    private String code;
    private LocalDateTime exportDate;
    private Long createdById;     
    private ExportType type;
    private ExportStatus status;
    private String note;
    private int isDeleted;
}
