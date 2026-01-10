package com.conveniencestore.constant;

/**
 * Enum thể hiện loại khuyến mãi
 * Tránh hard-code String "percent", "amount"
 */
public enum PromotionType {

    PERCENT("Giảm theo phần trăm"),   // Ví dụ: 10%
    AMOUNT("Giảm theo số tiền");       // Ví dụ: 50.000đ

    private final String displayName;

    PromotionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
