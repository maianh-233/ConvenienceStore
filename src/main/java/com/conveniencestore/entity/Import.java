package com.conveniencestore.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.conveniencestore.constant.ImportStatus;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "imports",
    indexes = {
        @Index(name = "idx_imports_supplier", columnList = "supplier_id"),
        @Index(name = "idx_imports_staff", columnList = "staff_id")
    }
)
@Getter
@Setter
public class Import {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID phiếu nhập, tự tăng

    @Column(name = "import_number", nullable = false, unique = true, length = 100)
    private String importNumber; // Mã phiếu nhập

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier; // Nhà cung cấp

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private User staff; // Nhân viên nhập hàng

    @Column(name = "total_amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO; // Tổng tiền phiếu nhập

    @Column(length = 500)
    private String note; // Ghi chú phiếu nhập

    @Column(name = "is_deleted", nullable = false)
    private int isDeleted = 1;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

        // ================== TRẠNG THÁI PHIẾU NHẬP ==================
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ImportStatus status;


    // ================== Chi tiết sản phẩm nhập ==================
    @OneToMany(mappedBy = "importRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImportItem> importItems;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = ImportStatus.PENDING; 
        }
    }


    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
