package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "categories",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_categories_name", columnNames = "name")
    }
)
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID danh mục, tự tăng

    @Column(nullable = false, length = 100, unique = true)
    private String name; // Tên danh mục, duy nhất

    @Column(length = 255)
    private String description; // Mô tả danh mục (tuỳ chọn)

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /* =================== QUAN HỆ VỚI PRODUCT =================== */
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products; // Danh sách sản phẩm thuộc danh mục này

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
