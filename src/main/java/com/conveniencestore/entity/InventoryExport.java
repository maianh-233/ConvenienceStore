package com.conveniencestore.entity;
import java.time.LocalDateTime;

import com.conveniencestore.constant.ExportStatus;
import com.conveniencestore.constant.ExportType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
    private int isDeleted = 1; 

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
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
