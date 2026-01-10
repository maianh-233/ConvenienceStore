package com.conveniencestore.constant;

/**
 * Enum thể hiện loại xuất kho
 * Dùng để xác định lý do xuất kho (bán hàng, hư hỏng, hết hạn, điều chỉnh...)
 */
public enum ExportType {

    SALE("Bán hàng"),           // Xuất kho do bán hàng
    DAMAGED("Hàng hư hỏng"),    // Xuất kho do hàng bị hư
    EXPIRED("Hết hạn sử dụng"), // Xuất kho do hàng hết hạn
    ADJUSTMENT("Điều chỉnh kho"); // Xuất kho do kiểm kê / điều chỉnh

    private final String displayName; // Tên hiển thị loại xuất kho

    ExportType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Lấy tên hiển thị của loại xuất kho
     */
    public String getDisplayName() {
        return displayName;
    }
}
