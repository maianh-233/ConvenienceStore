package com.conveniencestore.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
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

}
