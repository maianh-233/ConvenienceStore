package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDTO {

    private Long id;
    private String username;
    private String password;        
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String identityNumber;
    private String address;
    private String imgUrl;
    private int gender;
    private Long roleId;           
    private int active;
    private int locked;
}
