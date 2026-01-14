package com.conveniencestore.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.conveniencestore.constant.ExportStatus;
import com.conveniencestore.constant.ExportType;

@Entity
@Table(
    name = "inventory_exports",
    indexes = {
        @Index(name = "idx_export_code", columnList = "code"),
        @Index(name = "idx_export_created", columnList = "created_at")
    }
)
@Getter
@Setter
public class InventoryExport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================== THÔNG TIN PHIẾU ==================
    @Column(nullable = false, unique = true, length = 30)
    private String code; // PXK-2026-0001


    // ================== NGƯỜI THỰC HIỆN ==================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    // ================== LOẠI XUẤT ==================
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ExportType type;

    // ================== TRẠNG THÁI ==================
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ExportStatus status;

    // ================== GHI CHÚ ==================
    @Column(length = 255)
    private String note;

     @Column(name = "is_deleted", nullable = false)
    private int isDeleted = 1; // 1 = chưa xóa, 0 = đã xóa

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
        if (status == null) status = ExportStatus.SUCCESS;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
