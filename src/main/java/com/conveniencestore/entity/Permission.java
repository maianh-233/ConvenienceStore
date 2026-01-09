package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "permissions",
    uniqueConstraints = {
        @UniqueConstraint(name = "ux_permissions_name", columnNames = "name")
    }
)
@Getter
@Setter
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "group_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_permissions_group")
    )
    private PermissionGroup group;
}
