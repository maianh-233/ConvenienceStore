package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(
    name = "import_items",
    indexes = {
        @Index(name = "idx_import_item_import", columnList = "import_id"),
        @Index(name = "idx_import_item_product", columnList = "product_id")
    }
)
@Getter
@Setter
public class ImportItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID chi tiết nhập

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "import_id", nullable = false)
    private Import importRecord; // Liên kết phiếu nhập

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Sản phẩm nhập

    @Column(nullable = false)
    private int quantity = 1; // Số lượng nhập

    @Column(name = "unit_price", precision = 12, scale = 2, nullable = false)
    private BigDecimal unitPrice = BigDecimal.ZERO; // Giá nhập 1 sản phẩm

    @Column(name = "total_price", precision = 12, scale = 2, nullable = false)
    private BigDecimal totalPrice = BigDecimal.ZERO; // Tổng tiền = quantity * unitPrice
}
