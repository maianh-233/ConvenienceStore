package com.conveniencestore.constant;

/**
 * Enum thể hiện trạng thái của phiếu nhập kho
 */
public enum ImportStatus {

    PENDING("Chờ nhập"),          // Phiếu mới tạo, chưa nhập kho
    COMPLETED("Đã nhập kho"),     // Nhập kho thành công
    CANCELLED("Đã hủy");          // Phiếu nhập bị hủy

    private final String displayName; // Tên hiển thị trạng thái

    ImportStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Lấy tên hiển thị trạng thái phiếu nhập
     */
    public String getDisplayName() {
        return displayName;
    }
}
