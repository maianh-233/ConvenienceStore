package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String username;
    private String fullName;
    private String passwordHash;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String imgUrl;
    private String imgUrlID;
    private int gender;
    private Long roleId;
    private String roleName;
    private int active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

   
}
