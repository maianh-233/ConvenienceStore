package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

import com.conveniencestore.constant.CustomerTier;

@Getter
@Setter
public class CustomerRequestDTO {

    private Long id;

    private String fullName;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String identityNumber;
    private int gender;

    private int isDeleted;

    private CustomerTier tier;
    private int points;

    private String password; // password raw từ form
}
