package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.conveniencestore.constant.ExportStatus;
import com.conveniencestore.constant.ExportType;

@Getter
@Setter
public class InventoryExportResponseDTO {

    private Long id;
    private String code;
    private Long createdById;
    private String createdByName;
    private ExportType type;
    private ExportStatus status;
    private String note;
    private int isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
