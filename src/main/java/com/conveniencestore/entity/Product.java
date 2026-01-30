package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "products",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_products_sku", columnNames = "sku")
    },
    indexes = {
        @Index(name = "idx_products_category", columnList = "category_id"),
        @Index(name = "idx_products_supplier", columnList = "supplier_id")
    }
)
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true)
    private String sku; // Mã sản phẩm

    // Thêm barciode cho sản phẩm
    @Column(length = 100, unique = true)
    private String barcode; // Mã vạch sản phẩm

    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;

    // Quan hệ với category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    // Quan hệ với supplier
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // Quan hệ với unit
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(precision = 12, scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    @Column(precision = 12, scale = 2)
    private BigDecimal cost;

    @Column(length = 500)
    private String description;

    @Column(length = 1024)
    private String imageUrl;

     // Thêm thuộc tính để có thể xóa file trên cloudinary
    @Column(name = "img_urlID")
    private String imgUrlID;

    @Column(name = "is_active", nullable = false)
    private int isActive = 1; 

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    // =================== QUAN HỆ ===================
    // 1 product có thể xuất hiện trong nhiều order items
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    // 1 product có thể xuất hiện trong nhiều import items
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImportItem> importItems;

    // 1 product có thể xuất hiện trong nhiều chi tiết phiếu xuất kho
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<InventoryExportItem> exportItems;

    // JOIN để lấy thông tin tồn kho
    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private Inventory inventory;



    // =================== TỰ ĐỘNG SET THỜI GIAN ===================
    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
