package com.conveniencestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Mã quyền

    @Column(nullable = false, unique = true, length = 50)
    private String name; // Tên chức vụ 

    @Column(name = "is_active", nullable = false)
    private int isActive; // 1 = active, 0 = inactive
}
