package com.conveniencestore.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionRequestDTO {

    private RolePermissionIdDTO id;

    private Long roleId;

    private Long permissionId;

    private int isActive;
}
