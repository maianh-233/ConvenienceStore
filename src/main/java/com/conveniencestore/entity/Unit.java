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

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="is_deleted",nullable = false)
    private int isDeleted = 1; 

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
