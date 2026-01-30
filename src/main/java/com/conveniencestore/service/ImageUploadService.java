package com.conveniencestore.service;

import java.io.File;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.conveniencestore.DTO.ImageUploadResultDTO;
import com.conveniencestore.config.CloudinaryConfig;
import com.conveniencestore.util.CloudinaryUtil;

public class ImageUploadService {
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();

    public ImageUploadResultDTO upload(File file) {
        try {
            Map<?, ?> result = cloudinary.uploader().upload(
                    file,
                    ObjectUtils.asMap(
                            "folder", CloudinaryConfig.FOLDER));

            return new ImageUploadResultDTO(
                    result.get("secure_url").toString(),
                    result.get("public_id").toString());

        } catch (Exception e) {
            throw new RuntimeException("Upload image failed", e);
        }
    }

    public void delete(String publicId) {
        try {
            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.emptyMap());
        } catch (Exception e) {
            throw new RuntimeException("Delete image failed", e);
        }
    }

}