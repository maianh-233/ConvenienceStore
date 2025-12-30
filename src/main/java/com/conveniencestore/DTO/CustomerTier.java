package com.conveniencestore.DTO;

/**
 * Enum thể hiện hạng khách hàng
 * Dùng để tránh viết trực tiếp String "REGULAR", "VIP", "PREMIUM"
 */
public enum CustomerTier {
    REGULAR("Khách thường"),   // Hạng bình thường
    VIP("Khách VIP"),          // Hạng VIP
    PREMIUM("Khách cao cấp");  // Hạng cao cấp

    private final String displayName; // Tên hiển thị

    CustomerTier(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Lấy tên hiển thị của hạng khách
     */
    public String getDisplayName() {
        return displayName;
    }
}
