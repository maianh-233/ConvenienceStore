package com.conveniencestore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageUploadResult {

    private String url;
    private String publicId;
}
