package com.conveniencestore.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionResponseDTO {

    private RolePermissionIdDTO id;

    private RoleResponseDTO role;

    private PermissionResponseDTO permission;

    private int isActive;
}
