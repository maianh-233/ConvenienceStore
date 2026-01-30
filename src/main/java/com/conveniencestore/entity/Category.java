package com.conveniencestore.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted",nullable = false)
    private int isDeleted = 1; 

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
