package com.conveniencestore.constant;

/**
 * Enum thể hiện trạng thái của đơn hàng
 * Dùng để tránh viết trực tiếp String "pending", "shipped", "delivered",
 * "cancelled"...
 */
public enum OrderStatus {
	PENDING("Chờ xử lý"), // Đơn hàng mới, chưa xử lý
	PROCESSING("Đang xử lý"), // Đơn hàng đang được xử lý
	SHIPPED("Đã giao"), // Đơn hàng đã giao cho shipper
	DELIVERED("Hoàn thành"), // Đơn hàng đã giao đến khách
	CANCELLED("Đã hủy"); // Đơn hàng bị hủy

	private final String displayName; // Tên hiển thị trạng thái

	OrderStatus(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Lấy tên hiển thị trạng thái
	 */
	public String getDisplayName() {
		return displayName;
	}
}
