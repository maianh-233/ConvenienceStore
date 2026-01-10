package com.conveniencestore.entity;
mport jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(
    name = "inventory_export_items",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "ux_export_product",
            columnNames = {"export_id", "product_id"}
        )
    }
)
@Getter
@Setter
public class InventoryExportItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================== PHIẾU XUẤT ==================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "export_id", nullable = false)
    private InventoryExport export;

    // ================== SẢN PHẨM ==================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // ================== SỐ LƯỢNG ==================
    @Column(nullable = false)
    private int quantity; // số lượng xuất (>0)


    // ================== GHI CHÚ ==================
    @Column(length = 255)
    private String note;
}
