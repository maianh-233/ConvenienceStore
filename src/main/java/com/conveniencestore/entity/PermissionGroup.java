package com.conveniencestore.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(
    name = "permission_groups",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_permission_groups_code", columnNames = "code")
    }
)
@Getter
@Setter
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String code;
    // ORDER, PRODUCT, CUSTOMER

    @Column(nullable = false, length = 100)
    private String name;
    // Quản lý đơn hàng, Quản lý sản phẩm
}
