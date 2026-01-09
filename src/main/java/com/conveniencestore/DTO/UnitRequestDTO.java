package com.conveniencestore.DTO;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UnitRequestDTO {
    private Long id;          // null khi thêm, có khi sửa
    private String name;      // tên danh mục
    private String description;
    private int isDeleted;    // 0 = active, 1 = inactive
    
  
}