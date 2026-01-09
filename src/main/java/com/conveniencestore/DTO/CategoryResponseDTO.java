package com.conveniencestore.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int isDeleted; // 0 = active, 1 = inactive

   

}