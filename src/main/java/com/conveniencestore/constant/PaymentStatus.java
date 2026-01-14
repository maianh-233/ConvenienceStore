package com.conveniencestore.constant;

/**
 * Enum thể hiện trạng thái của thanh toán
 * Dùng để tránh viết trực tiếp String "pending", "completed", "failed", ...
 */
public enum PaymentStatus {
	PENDING("Đang chờ"),
	COMPLETED("Hoàn thành"),
	FAILED("Thất bại"),
	CANCELLED("Đã hủy");

	private final String displayName;

	PaymentStatus(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Lấy tên hiển thị trạng thái thanh toán
	 */
	public String getDisplayName() {
		return displayName;
	}
}
