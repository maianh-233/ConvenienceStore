package com.conveniencestore.DTO;

public class ImageUploadResultDTO {

    private final String url;
    private final String publicId;

    public ImageUploadResultDTO(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicId() {
        return publicId;
    }
}
