package com.conveniencestore.DTO;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UnitResponseDTO {
    private Long id;
    private String name;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int isDeleted; // 0 = active, 1 = inactive

   
}