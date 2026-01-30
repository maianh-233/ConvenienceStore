package com.conveniencestore.DTO;

import com.conveniencestore.constant.PermissionAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseDTO {

    private Long id;

    private String name;

    private String description;

    private PermissionAction permissionAction;

    private PermissionGroupResponseDTO group;
}
