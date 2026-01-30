package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UserRequestDTO {

    private Long id;
    private String username;
    private String fullName;  
    private String password;        
    private LocalDate dateOfBirth;
    private String email;
    private String phone;
    private String address;
    private String imgUrl;
    private String imgUrlID;
    private int gender;
    private Long roleId;           
    private int active;

}
