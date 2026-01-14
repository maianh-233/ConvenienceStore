package com.conveniencestore.constant;

/**
 * Enum thể hiện trạng thái của phiếu xuất kho
 * Dùng để theo dõi kết quả xử lý xuất kho
 */
public enum ExportStatus {

	SUCCESS("Thành công"), // Xuất kho thành công
	FAILED("Thất bại"), // Xuất kho thất bại (hết hàng, lỗi hệ thống...)
	CANCELED("Đã hủy"); // Phiếu xuất kho bị hủy

	private final String displayName; // Tên hiển thị trạng thái

	ExportStatus(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Lấy tên hiển thị trạng thái xuất kho
	 */
	public String getDisplayName() {
		return displayName;
	}
}
