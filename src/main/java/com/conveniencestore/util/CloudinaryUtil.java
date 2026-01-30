package com.conveniencestore.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.conveniencestore.config.CloudinaryConfig;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    private CloudinaryUtil() {}

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CloudinaryConfig.CLOUD_NAME,
                "api_key", CloudinaryConfig.API_KEY,
                "api_secret", CloudinaryConfig.API_SECRET
            ));
        }
        return cloudinary;
    }
}
