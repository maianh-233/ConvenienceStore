package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.conveniencestore.constant.CustomerTier;

@Getter
@Setter
public class CustomerResponseDTO {

    private Long id;

    private String fullName;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private int gender;

    private CustomerTier tier;
    private int points;

    private String password; // password hashed lưu DB
    private int isDeleted;

     /* =================== THÔNG TIN AUDIT =================== */
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
