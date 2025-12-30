package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permissions",
       uniqueConstraints = {@UniqueConstraint(name = "ux_permissions_name", columnNames = "name")})
@Getter
@Setter
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID quyền, tự tăng

    @Column(nullable = false, length = 100, unique = true)
    private String name; // Tên quyền chi tiết, ví dụ: ORDER_CREATE, PRODUCT_EDIT

    @Column(length = 255)
    private String description; // Mô tả quyền, ví dụ: "Cho phép tạo đơn hàng"
}
