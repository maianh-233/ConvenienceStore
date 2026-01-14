package com.conveniencestore.constant;

public enum PermissionAction {

    VIEW("Xem"),
    CREATE("Thêm"),
    UPDATE("Sửa"),
    DELETE("Xóa"),
    RESTORE("Khôi phục");

    private final String displayName;

    PermissionAction(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Lấy tên hiển thị
     */
    public String getDisplayName() {
        return displayName;
    }
}
