package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "units",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_units_name", columnNames = "name")
    }
)
@Getter
@Setter
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID đơn vị, tự tăng

    @Column(nullable = false, length = 50, unique = true)
    private String name; // Tên đơn vị: hộp, chiếc, cái, chai...

    @Column(length = 255)
    private String description; // Mô tả đơn vị

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private int isDeleted = 1; // 1 = chưa xóa, 0 = đã xóa

    /* =================== QUAN HỆ VỚI PRODUCT =================== */
    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY)
    private List<Product> products; // Danh sách sản phẩm thuộc đơn vị này

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
